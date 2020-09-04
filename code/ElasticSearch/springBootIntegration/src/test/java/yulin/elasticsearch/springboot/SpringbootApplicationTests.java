package yulin.elasticsearch.springboot;

import com.alibaba.fastjson.JSON;
import net.bytebuddy.asm.Advice;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.MatchAllQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import yulin.elasticsearch.springboot.pojo.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

@SpringBootTest
class SpringbootApplicationTests {

    @Autowired
    RestHighLevelClient restHighLevelClient;

    /**
     * 测试索引的创建
     */
    @Test
    void testCreateIndex() throws IOException {
        //创建索引请求
        CreateIndexRequest request = new CreateIndexRequest("test_index");
        //客户端执行请求
        CreateIndexResponse createIndexResponse = restHighLevelClient.indices().create(request, RequestOptions.DEFAULT);

        System.out.println(createIndexResponse);
    }

    /**
     * 判断索引是否存在
     */
    @Test
    void testExistIndex() throws IOException {
        GetIndexRequest request = new GetIndexRequest("test_index");
        boolean exists = restHighLevelClient.indices().exists(request, RequestOptions.DEFAULT);
        System.out.println(exists);
    }

    /**
     * 测试删除索引
     */
    @Test
    void testDeleteIndex() throws IOException {
        DeleteIndexRequest request = new DeleteIndexRequest("test_index");
        AcknowledgedResponse acknowledgedResponse = restHighLevelClient.indices().delete(request, RequestOptions.DEFAULT);
        System.out.println(acknowledgedResponse.isAcknowledged());
    }

    /**
     * 测试增加文档
     * @throws IOException
     */
    @Test
    void testAddDocument() throws IOException {
        //创建新索引请求
        IndexRequest indexRequest = new IndexRequest("test_index");
        //设置索引id : “put test_index/_doc/1” 中的1
        indexRequest.id("1");
        indexRequest.timeout(TimeValue.timeValueSeconds(1));
        //使用阿里巴巴的fastjson将自定义pojo对象转换成json格式，并放入请求中
        indexRequest.source(JSON.toJSONString(new User("yulin",23)), XContentType.JSON);
        //获取索引响应
        IndexResponse indexResponse = restHighLevelClient.index(indexRequest, RequestOptions.DEFAULT);
        //查看响应结果
        System.out.println(indexResponse.toString());
        System.out.println(indexResponse.status());
    }

    /**
     * 测试更新文档
     */
    @Test
    void testUpdateDocument() throws IOException {
        UpdateRequest updateRequest = new UpdateRequest("test_index", "1");
        updateRequest.timeout("1s");

        updateRequest.doc(JSON.toJSONString(new User("yulin!!", 33)), XContentType.JSON);
        UpdateResponse updateResponse = restHighLevelClient.update(updateRequest, RequestOptions.DEFAULT);

        System.out.println(updateResponse.toString());
        System.out.println(updateResponse.status());
    }

    /**
     * 测试查找文档
     */
    @Test
    void testGetDocument() throws IOException {
        GetRequest getRequest = new GetRequest("test_index", "1");
        GetResponse getResponse = restHighLevelClient.get(getRequest, RequestOptions.DEFAULT);

        System.out.println(getResponse.toString());
        System.out.println(getResponse.getIndex());
        System.out.println(getResponse.getSource());
    }

    /**
     * 测试删除文档
     */
    @Test
    void testDeleteDocument() throws IOException {
        DeleteRequest deleteRequest = new DeleteRequest("test_index", "1");
        deleteRequest.timeout("1s");

        DeleteResponse deleteResponse = restHighLevelClient.delete(deleteRequest, RequestOptions.DEFAULT);
        System.out.println(deleteResponse.toString());
        System.out.println(deleteResponse.status());
    }

    /**
     * 批量插入数据
     */
    @Test
    void testBulkRequest() throws IOException {
        BulkRequest bulkRequest = new BulkRequest();
        bulkRequest.timeout("1s");

        ArrayList<User> list = new ArrayList<>();
        list.add(new User("yulin1",11));
        list.add(new User("yulin2",12));
        list.add(new User("yulin3",13));
        list.add(new User("yulin4", 14));

        //这里是批量插入，我们也可以使用批量删除修改查找等，只需要找重载的add方法
        for (int i = 0; i < list.size(); i++) {
            bulkRequest.add(
                    new IndexRequest("test_index").id(""+(i+1))
                            .source(JSON.toJSONString(list.get(i)),XContentType.JSON));
        }
        BulkResponse bulkResponse = restHighLevelClient.bulk(bulkRequest, RequestOptions.DEFAULT);
        System.out.println(bulkResponse.toString());
        System.out.println(bulkResponse.hasFailures());
        System.out.println(bulkResponse.status());
    }

    @Test
    void testSearch() throws IOException {
        SearchRequest searchRequest = new SearchRequest("test_index");
        //构建搜索条件
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        //查询条件，我们可以使用QueryBuilders工具类来实现
        //MatchAllQueryBuilder matchAllQueryBuilder = QueryBuilders.matchAllQuery();
        //TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery();
        TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery("name", "yulin1");
        searchSourceBuilder.query(termQueryBuilder);

        searchSourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));

        searchRequest.source(searchSourceBuilder);

        SearchResponse searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);

        System.out.println(searchResponse.getHits());
        System.out.println(Arrays.toString(searchResponse.getHits().getHits()));
    }
}

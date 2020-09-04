package yulin.elasticSearch.Utils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class HtmlParseUtils {
    public static void main(String[] args) throws IOException {
        String url = "https://search.jd.com/Search?keyword=java";
        Document document = Jsoup.parse(new URL(url), 10000);

        Element element = document.getElementById("J_goodsList");
        System.out.println(element.html());

    }
}

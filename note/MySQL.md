```sql
-- 1、查询"01"课程比"02"课程成绩高的学生的信息及课程分数
select a.*, s1.s_score, s2.s_score from student a
join score s1 on a.s_id = s1.s_id and s1.c_id = "01"
left join score s2 on s1.s_id = s2.s_id and s2.c_id = "02"
where s1.s_score > s2.s_score;


-- 2、查询"01"课程比"02"课程成绩低的学生的信息及课程分数 
select stu.*, s1.s_score as "score1", s2.s_score as "score2" from student stu 
join score s1 on stu.s_id = s1.s_id and s1.c_id = "01"
join score s2 on stu.s_id = s2.s_id and s2.c_id = "02"
where s1.s_score < s2.s_score;


-- 3、查询平均成绩大于等于60分的同学的学生编号和学生姓名和平均成绩
select stu.s_id, stu.s_name,  AVG(s.s_score) as "moyen"
from student stu
join score s
on stu.s_id = s.s_id
group by s.s_id
having AVG(s.s_score)>=60;
```


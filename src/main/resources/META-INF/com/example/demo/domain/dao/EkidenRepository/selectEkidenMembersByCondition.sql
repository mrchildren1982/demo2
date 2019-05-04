select
/*%expand */*
from t_ekiden_members
where
/*%if condition.membersId != null */
id = /* condition.membersId */1
/*%end*/
/*%if condition.name  != null && !condition.name.isEmpty() */
and name = /* condition.name */'ほげ'
/*%end*/
/*%if condition.sex != null && !condition.sex.isEmpty() */
and sex = /*condition.sex*/'男'
/*%end*/
order by
id,
name
,sex;

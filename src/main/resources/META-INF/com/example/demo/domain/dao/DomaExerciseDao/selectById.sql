select
/*%expand */*
from
t_doma
where
/*%if id != null */
id = /* id */1
/*%end */
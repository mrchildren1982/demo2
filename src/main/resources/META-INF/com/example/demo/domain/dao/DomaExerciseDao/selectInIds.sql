select
/*%expand */*
from t_doma
where
/*%for id:ids */
id in /*id*/1
/*%end*/
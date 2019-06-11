select
/*%expand */*
from attachment
where
create_date <= /* to */'2019-12-01'
order by create_date;
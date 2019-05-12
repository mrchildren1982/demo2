select
custum_interval as custum_interval
from t_polling_interval p
where
/*%if stbSerial!= null  && stbSerial.length() != 0 */
p.stb_serial = /*stbSerial */1
/*%end */
union all
select
polling_interval as custum_interval
from t_default_polling_interval d
where
/*%if !defaultSearchFlag */
polling_interval is null
/*%end */

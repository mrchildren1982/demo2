select
  /*%expand*/*
from
  t_ekiden_members
where
  id in /*ids*/(1, 2, 3)

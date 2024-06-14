select  USER_ID, NICKNAME, SUM(PRICE) TOTAL_SALES
from    USED_GOODS_BOARD right outer join USED_GOODS_USER
        on WRITER_ID = USER_ID
where   STATUS = "DONE"
group by USER_ID
having  SUM(PRICE) >= 700000
order by TOTAL_SALES asc;

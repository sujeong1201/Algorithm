select USER_ID, PRODUCT_ID
from (select count(*) as PURCHASE_CNT, USER_ID, PRODUCT_ID
      from ONLINE_SALE
      group by USER_ID, PRODUCT_ID) COUNT_TABLE
where PURCHASE_CNT >= 2
order by USER_ID, PRODUCT_ID desc;

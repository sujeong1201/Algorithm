select  RI.FOOD_TYPE, REST_ID, REST_NAME, FAVORITES
from    REST_INFO RI, (
        select FOOD_TYPE, max(FAVORITES) MAX_FAV
        from REST_INFO
        group by FOOD_TYPE) MF
where   RI.FOOD_TYPE = MF.FOOD_TYPE
        and RI.FAVORITES = MF.MAX_FAV
order by FOOD_TYPE desc;
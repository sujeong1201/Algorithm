select  F.flavor
from    (select FLAVOR, sum(TOTAL_ORDER) total
        from FIRST_HALF
        group by FLAVOR) F,
        (select FLAVOR, sum(TOTAL_ORDER) total
        from JULY
        group by FLAVOR) J
where   F.FLAVOR = J.FLAVOR
order by (F.total + J.total) desc
limit 3;
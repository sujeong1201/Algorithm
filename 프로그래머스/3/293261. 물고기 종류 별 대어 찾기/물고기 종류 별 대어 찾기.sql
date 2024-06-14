select  ID, FISH_NAME, FISH_INFO.LENGTH
from    FISH_INFO, FISH_NAME_INFO, (
            select FISH_TYPE, max(LENGTH) length
            from FISH_INFO
            group by FISH_TYPE
        ) T
where   FISH_INFO.FISH_TYPE = FISH_NAME_INFO.FISH_TYPE
        and FISH_INFO.FISH_TYPE = T.FISH_TYPE
        and FISH_INFO.LENGTH = T.length
order by ID asc;
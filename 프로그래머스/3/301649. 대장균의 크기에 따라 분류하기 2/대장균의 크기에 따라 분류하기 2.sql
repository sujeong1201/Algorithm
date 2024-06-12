# select count(*) into @num from ECOLI_DATA;
# select @num;

select ID, 
    case
        when row_number() over (order by SIZE_OF_COLONY desc) <= (select count(*) from ECOLI_DATA) * 0.25
        then 'CRITICAL'
        when row_number() over (order by SIZE_OF_COLONY desc) <= (select count(*) from ECOLI_DATA) * 0.5
        then 'HIGH'
        when row_number() over (order by SIZE_OF_COLONY desc) <= (select count(*) from ECOLI_DATA) * 0.75
        then 'MEDIUM'
        else 'LOW'
    end as COLONY_NAME
from ECOLI_DATA
order by ID;
select FIRST 1 BDCODCNAE from TEMPRESAS_REF
where BDCODEMP = :enterpriseCode and BDREFEMP <= :reference
ORDER BY BDREFEMP DESC
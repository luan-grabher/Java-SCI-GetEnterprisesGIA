select FIRST 1
t.BDCODFPASTERC,
T.BDSIMPLESGPS,
T.BDCODFPAS,
T.BDPERTERC porcentagem_terceiro,
T.BDEMPEMP as empregadores,
T.BDAUTEMP as autonomos,
t.BDFUNEMP as colaboradores
from VWRH_EMPMENSAL_ABA_GPS t
where t.BDCODEMP = :enterpriseCode and t.BDREFEMP <= :reference
ORDER BY T.BDREFEMP DESC
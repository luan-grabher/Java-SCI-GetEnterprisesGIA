SELECT
t.BDCODEMP,
count(*) as conta,
/*t.BDCODCOL,*/
t.BDREF,
sum(t.BDBINSSFN),
sum(t.BDBASEINSS13),
iif(c.BDTIPOADMCOL=0,'empregador','colaborador') as tipo_folha FROM VW_INSS_FOL_NORMAL T
inner join VW_COLABORADORES c
on c.BDCODEMP = :enterpriseCode and c.BDCODCOL = t.BDCODCOL
WHERE T.BDCODEMP = :enterpriseCode AND t.BDREF = :reference
group by t.BDCODEMP, t.BDREF, c.BDTIPOADMCOL
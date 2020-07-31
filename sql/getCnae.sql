SELECT FIRST 1 * FROM TCNAE_REF
where bdcodcnae = :cnae and BDREFCNAE <= :reference
ORDER BY BDREFCNAE DESC
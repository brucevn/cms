GRANT SELECT ON wwsec_person TO trungbx;
GRANT SELECT ON wwsec_person$ TO trungbx;
GRANT EXECUTE ON wwctx_api TO trungbx;
GRANT EXECUTE ON wwsec_api TO trungbx;
GRANT SELECT ON wwsec_group$ TO trungbx;
GRANT SELECT ON wwsec_group TO trungbx;
GRANT EXECUTE ON WWCTX_SSO TO trungbx;
GRANT EXECUTE ON WWCTX_API TO trungbx;
ORA-06510: PL/SQL: unhandled user-defined exception
ORA-06512: at "PORTAL.WWCTX_SSO", line 1803
ORA-06510: PL/SQL: unhandled user-defined exception
ORA-06512: at "PORTAL.WWCTX_SSO", line 1637
ORA-06502: PL/SQL: numeric or value error
ORA-06512: at "PORTAL.WWCTX_SSO", line 1922
ORA-06512: at "PORTAL.WWCTX_API", line 279
ORA-06512: at "PORTAL.WWERR_API_ERROR", line 99
ORA-06512: at "PORTAL.WWERR_API_ERROR", line 222
ORA-06512: at "PORTAL.WWCTX_SSO", line 3022
ORA-14551: cannot perform a DML operation inside 


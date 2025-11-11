package kz.bsbnb.usci.wsclient.dao;

import kz.bsbnb.usci.model.exception.UsciException;
import kz.bsbnb.usci.util.SqlJdbcConverter;
import kz.bsbnb.usci.util.json.ext.ExtJsList;
import kz.bsbnb.usci.wsclient.model.ctrkgd.Request;
import kz.bsbnb.usci.wsclient.model.ctrkgd.RequestStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Repository
public class KGDDaoImpl implements KGDDao {
    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate npJdbcTemplate;
    private final SimpleJdbcInsert requestInsert;
    private String selectSql;

    public KGDDaoImpl(NamedParameterJdbcTemplate npJdbcTemplate, JdbcTemplate jdbcTemplate) {
        this.npJdbcTemplate = npJdbcTemplate;
        this.jdbcTemplate = jdbcTemplate;

        this.requestInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withSchemaName("USCI_WS")
                .withTableName("CTR_KGD")
                .usingColumns("REPORT_DATE","SEND_DATE","STATUS_ID", "REQUEST_BODY", "RESPONSE_BODY", "ENTITIES_COUNT", "REQUEST_ID","OPERATION_ID")
                .usingGeneratedKeyColumns("ID");
    }

    @Override
    public List<Map<String, Object>> entityRows(LocalDate reportDate, LocalDateTime periodDate, int operationId) {

        selectSql="select * from (select " ;
        if(operationId == 1){selectSql +="   'NEW' as message_type,";}
        if(operationId == 2){selectSql +="   'EDIT' as message_type,";}
        if(operationId == 3){
            selectSql +="                    'DELETE' as message_type," +
                    "                    t.reference, " +
                    "                        t.system_date correction_date,  "+
                    "                         (select r.code_alpha_2 " +
                    "                            from eav_data.ref_country r, " +
                    "                                 eav_data.ctr_subject c " +
                    "                           where r.entity_id = c.ref_country_id " +
                    "                             and c.entity_id = t.beneficiary_id " +
                    "                             and c.report_date = (Select max(report_date) " +
                    "                                                    from eav_data.ctr_subject cs2 " +
                    "                                                   where cs2.entity_id = c.entity_id " +
                    "                                                     and cs2.report_date <= t.report_date)" +
                    "                             and r.report_date = (Select max(rf2.report_date) " +
                    "                                                   from  eav_data.ref_country rf2 " +
                    "                                                   where rf2.entity_id = r.entity_id " +
                    "                                                     and rf2.report_date <=  t.report_date) ) as beneficiary_country_code , " +
                    "                          (select r.code_alpha_2 " +
                    "                             from eav_data.ref_country r, " +
                    "                                  eav_data.ctr_subject c " +
                    "                            where r.entity_id = c.ref_country_id " +
                    "                              and c.entity_id = t.sender_id " +
                    "                              and c.report_date = (Select max(report_date) " +
                    "                                                     from eav_data.ctr_subject cs2 " +
                    "                                                    where cs2.entity_id = c.entity_id " +
                    "                                                      and cs2.report_date <= t.report_date)" +
                    "                              and r.report_date = (Select max(rf2.report_date) " +
                    "                                                    from  eav_data.ref_country rf2 " +
                    "                                                    where rf2.entity_id = r.entity_id " +
                    "                                                      and rf2.report_date <=  t.report_date)) as sender_country_code  " +
                    "                       from eav_data.ctr_transaction t , " +
                    "                            eav_data.V_CURRENCY_USD_TG_KGD cur " +
                    "                      where t.cont_date is not null " +
                    "                        and t.cont_num is not null " +
                    "                        and t.ref_curr_trans_code_id in (1,2,3,15,8,14,11,6,5,4,17,22,18,13,12,10,25,9,20,7,19,16,24,27,30,21,28,31,23,26,29,34,33,32,42,43,45,46,48,49,50,54,55)\n" +
                    "                        and t.entity_id = cur.entity_id " +
                    "                        and t.report_date = cur.report_date " +
                    "                        and (t.cont_sum * cur.course_curr /cur.corellation/ cur.course_usd) > 50 " +
                    "                        and t.report_date = (Select max(report_date) " +
                    "                                               from eav_data.ctr_transaction t2 " +
                    "                                              where t2.entity_id = t.entity_id " +
                    "                                                and t2.report_date <= t.report_date) " +
                    "                        and exists (select 1 " +
                    "                                  from eav_data.ctr_transaction ctr3 " +
                    "                                 where ctr3.entity_id = t.entity_id " +
                    "                                   and ctr3.creditor_id = t.creditor_id " +
                    "                                   and ctr3.operation_id = 3) " +
                    "                         and t.report_date < :REPORT_DATE " +
                    "                         and t.system_date >= add_months(:PERIOD_DATE,-1)" +
                    "                         and t.system_date < :PERIOD_DATE  "+
                    "                       union all "             +
                    "                   select 'DELETE' as message_type, " +
                    "                          dct.reference,  " +
                    "                          dct.system_date correction_date,  " +
                    "       (select r.code_alpha_2  " +
                    "          from eav_data.ref_country r,  " +
                    "               eav_data.ctr_subject c  " +
                    "         where r.entity_id = c.ref_country_id  " +
                    "           and c.entity_id = dct.beneficiary_id  " +
                    "           and c.report_date = (Select max(report_date)  " +
                    "                                  from eav_data.ctr_subject cs2  " +
                    "                                 where cs2.entity_id = c.entity_id  " +
                    "                                   and cs2.report_date <= dct.report_date)" +
                    "           and r.report_date = (Select max(rf2.report_date) " +
                    "                                 from  eav_data.ref_country rf2 " +
                    "                                 where rf2.entity_id = r.entity_id " +
                    "                                   and rf2.report_date <=  dct.report_date))  as beneficiary_country_code ,  " +
                    "        (select r.code_alpha_2  " +
                    "           from eav_data.ref_country r,  " +
                    "                eav_data.ctr_subject c  " +
                    "          where r.entity_id = c.ref_country_id  " +
                    "            and c.entity_id = dct.sender_id  " +
                    "            and c.report_date = (Select max(report_date)  " +
                    "                                   from eav_data.ctr_subject cs2  " +
                    "                                  where cs2.entity_id = c.entity_id  " +
                    "                                    and cs2.report_date <= dct.report_date)" +
                    "            and r.report_date = (Select max(rf2.report_date) " +
                    "                                  from  eav_data.ref_country rf2 " +
                    "                                  where rf2.entity_id = r.entity_id " +
                    "                                    and rf2.report_date <=  dct.report_date)) as sender_country_code   " +
                    "   from EAV_DATA.CTR_TRANSACTION dct," +
                    "       (select dense_rank() over (partition by dctn.entity_id order by dctn.report_date desc) as rnk," +
                    "               dctn.*" +
                    "          from EAV_DATA.CTR_TRANSACTION dctn) dctp," +
                    "               eav_data.V_CURRENCY_USD_TG_KGD cur   " +
                    "  where dctp.entity_id = dct.entity_id" +
                    "    and dctp.rnk = 2" +
                    "    and dct.cont_date is not null" +
                    "    and dct.cont_num is not null" +
                    "    and dctp.ref_curr_trans_code_id in(1,2,3,15,8,14,11,6,5,4,17,22,18,13,12,10,25,9,20,7,19,16,24,27,30,21,28,31,23,26,29,34,33,32,42,43,45,46,48,49,50,54,55) " +
                    "    and dct.ref_curr_trans_code_id not in (1,2,3,15,8,14,11,6,5,4,17,22,18,13,12,10,25,9,20,7,19,16,24,27,30,21,28,31,23,26,29,34,33,32,42,43,45,46,48,49,50,54,55) "  +
                    "    and dct.report_date = (select max(report_date)  " +
                    "                           from eav_data.ctr_transaction t2 " +
                    "                          where t2.entity_id = dct.entity_id"  +
                    "                            and t2.report_date <= dct.report_date)  " +
                    "    and dct.report_date < :REPORT_DATE   " +
                    "    and dct.system_date> add_months(:PERIOD_DATE,-1) " +
                    "    and dct.system_date <= :PERIOD_DATE"  +
                    "    and dct.entity_id = cur.entity_id " +
                    "    and dct.report_date = cur.report_date " +
                    "  union all"  +
                    "   select 'DELETE' as message_type,"  +
                    "           reference,  " +
                    "          system_date correction_date,  " +
                    "         (select r.code_alpha_2  " +
                    "            from eav_data.ref_country r,  " +
                    "                 eav_data.ctr_subject c  " +
                    "         where r.entity_id = c.ref_country_id  " +
                    "           and c.entity_id = ee.beneficiary_id  " +
                    "           and c.report_date = (select max(report_date)  " +
                    "                                  from eav_data.ctr_subject cs2  " +
                    "                                 where cs2.entity_id = c.entity_id  " +
                    "                                   and cs2.report_date <= ee.report_date)" +
                    "          and r.report_date = (Select max(rf2.report_date) " +
                    "                                from  eav_data.ref_country rf2 " +
                    "                                where rf2.entity_id =   r.entity_id " +
                    "                                  and rf2.report_date <= ee.report_date )) as beneficiary_country_code ,  " +
                    "        (select r.code_alpha_2  " +
                    "           from eav_data.ref_country r,  " +
                    "                eav_data.ctr_subject c  " +
                    "          where r.entity_id = c.ref_country_id  " +
                    "            and c.entity_id = ee.sender_id  " +
                    "            and c.report_date = (select max(report_date)  " +
                    "                                   from eav_data.ctr_subject cs2  " +
                    "                                  where cs2.entity_id = c.entity_id  " +
                    "                                    and cs2.report_date <= ee.report_date)" +
                    "            and r.report_date = (Select max(rf2.report_date) " +
                    "                                  from  eav_data.ref_country rf2 " +
                    "                                  where rf2.entity_id =   r.entity_id " +
                    "                                    and rf2.report_date <= ee.report_date )) as sender_country_code " +
                    "          from (   " +
                    "               select a.*" +
                    "                   from (select 'DELETE' as message_type, " +
                    "                                 dct.reference," +
                    "                                 dct.cont_sum cont_sum_cur," +
                    "                                 dctp.cont_sum cont_sum_prv," +
                    "                                 dct.entity_id," +
                    "                                 dct.system_date," +
                    "                                 dct.beneficiary_id beneficiary_id," +
                    "                                 dct.sender_id sender_id,  " +
                    "                                 dct.report_date," +
                    "                                (select nvl(cu.course, 1)" +
                    "                                       from usci_ws.nsi_currency cu" +
                    "                                          , eav_data.ref_currency rcu" +
                    "                                      where cu.currency_code = rcu.short_name" +
                    "                                        and rcu.entity_id = dct.ref_currency_id" +
                    "                                        and rcu.report_date =" +
                    "                                            (select max(report_date)" +
                    "                                               from eav_data.ref_currency rcu2" +
                    "                                              where rcu2.entity_id = rcu.entity_id" +
                    "                                                and rcu2.report_date <= dct.report_date)" +
                    "                                        and cu.begin_date <= dct.curr_trans_date" +
                    "                                        and cu.course_date =" +
                    "                                            (select max(cu2.course_date)" +
                    "                                               from usci_ws.nsi_currency cu2" +
                    "                                              where cu2.currency_id = cu.currency_id" +
                    "                                                and cu2.course_date <= dct.curr_trans_date)) course_curr," +
                    "                                  (select nvl(cu.corellation, 1)" +
                    "                                    from usci_ws.nsi_currency cu," +
                    "                                        eav_data.ref_currency rcu" +
                    "                                   where cu.currency_code = rcu.short_name" +
                    "                                     and rcu.entity_id = dct.ref_currency_id" +
                    "                                     and rcu.report_date =" +
                    "                                        (select max(report_date)" +
                    "                                           from eav_data.ref_currency rcu2" +
                    "                                          where rcu2.entity_id = rcu.entity_id" +
                    "                                            and rcu2.report_date <= dct.report_date)" +
                    "                                    and cu.begin_date <= dct.curr_trans_date " +
                    "                                    and cu.course_date =" +
                    "                                        (select max(cu2.course_date) " +
                    "                                           from usci_ws.nsi_currency cu2 " +
                    "                                          where cu2.currency_id = cu.currency_id " +
                    "                                            and cu2.course_date <= dct.curr_trans_date)) corellation," +
                    "                                  (select nvl(cu.course, 1) " +
                    "                                          from usci_ws.nsi_currency cu "  +
                    "                                         where cu.currency_id = 1075" +
                    "                                           and cu.begin_date <=dct.curr_trans_date"  +
                    "                                           and cu.course_date =" +
                    "                                                        (select max(cu2.course_date) " +
                    "                                                           from usci_ws.nsi_currency cu2"  +
                    "                                                          where cu2.currency_id = 1075 " +
                    "                                                            and cu2.course_date <= dct.curr_trans_date)) course_usd " +
                    "                                                " +
                    "                          from EAV_DATA.CTR_TRANSACTION dct, " +
                    "                               (select dense_rank() over (partition by dctn.entity_id order by dctn.report_date desc) as rnk, "  +
                    "                                       dctn.*" +
                    "                                  from EAV_DATA.CTR_TRANSACTION dctn) dctp  " +
                    "                          where dctp.entity_id = dct.entity_id " +
                    "                            and dctp.rnk = 2 " +
                    "                            and dct.report_date < :REPORT_DATE  " +
                    "                            and dct.cont_sum > 0 "  +
                    "                            and dct.cont_date is not null " +
                    "                            and dct.cont_num is not null " +
                    "                            and dct.system_date> add_months(:PERIOD_DATE,-1) " +
                    "                            and dct.system_date <= :PERIOD_DATE " +
                    "                            and dct.report_date = (select max(report_date)  " +
                    "                                                   from eav_data.ctr_transaction t2 "  +
                    "                                                  where t2.entity_id = dct.entity_id "  +
                    "                                                    and t2.report_date <= dct.report_date))a " +
                    "                 where (a.cont_sum_prv * a.course_curr /a.corellation/ a.course_usd) > 50    " +
                    "                   and (a.cont_sum_cur * a.course_curr /a.corellation/ a.course_usd) < 50)ee " ;
        }
        else {
            selectSql +=
                    "                         t.reference, " +
                            "                         t.system_date correction_date," +
                            "                         t.curr_trans_date, " +
                            "                         t.cont_sum, " +
                            "                         t.cont_num, " +
                            "                         t.cont_date, " +
                            "                         t.cont_reg_num , " +
                            "                         (select r.code_alpha_2 " +
                            "                            from eav_data.ref_country r, " +
                            "                                 eav_data.ctr_subject c " +
                            "                           where r.entity_id = c.ref_country_id " +
                            "                             and c.entity_id = t.beneficiary_id " +
                            "                             and c.report_date = (Select max(report_date) " +
                            "                                                    from eav_data.ctr_subject cs2 " +
                            "                                                   where cs2.entity_id = c.entity_id " +
                            "                                                     and cs2.report_date <= t.report_date)" +
                            "                             and r.report_date = (Select max(rf2.report_date) " +
                            "                                                   from  eav_data.ref_country rf2 " +
                            "                                                   where rf2.entity_id =   r.entity_id " +
                            "                                                     and rf2.report_date <= t.report_date ) ) as beneficiary_country_code , " +
                            "                          (select r.code " +
                            "                             from eav_data.ref_residency r, " +
                            "                                  eav_data.ctr_subject c " +
                            "                            where r.entity_id = c.ref_residency_id " +
                            "                              and c.entity_id = t.beneficiary_id " +
                            "                              and c.report_date = (Select max(report_date) " +
                            "                                                     from eav_data.ctr_subject cs2 " +
                            "                                                    where cs2.entity_id = c.entity_id " +
                            "                                                      and cs2.report_date <= t.report_date)) as beneficiary_residency_code ," +
                            "                          (select c.name " +
                            "                             from eav_data.ctr_subject c " +
                            "                            where c.entity_id = t.beneficiary_id " +
                            "                              and c.report_date = (Select max(report_date) " +
                            "                                                     from eav_data.ctr_subject cs2 " +
                            "                                                    where cs2.entity_id = c.entity_id " +
                            "                                                      and cs2.report_date <= t.report_date)) as beneficiary_name ,  " +
                            //"                       (select c.bin_iin_id from eav_data.ctr_subject c \n" +
                            "                          (select c.bin_iin " +
                            "                             from eav_data.ctr_subject c " +
                            "                            where c.entity_id = t.beneficiary_id " +
                            "                              and c.report_date = (Select max(report_date) " +
                            "                                                     from eav_data.ctr_subject cs2 " +
                            "                                                    where cs2.entity_id = c.entity_id " +
                            "                                                      and cs2.report_date <= t.report_date)) as beneficiary_biniin ,   " +
                            "                          (select r.code " +
                            "                             from eav_data.ref_econ_sector r, " +
                            "                                  eav_data.ctr_subject c " +
                            "                            where r.entity_id = c.ref_econ_sector_id " +
                            "                             and c.entity_id = t.beneficiary_id " +
                            "                             and c.report_date = (Select max(report_date) " +
                            "                                                    from eav_data.ctr_subject cs2 " +
                            "                                                   where cs2.entity_id = c.entity_id " +
                            "                                                     and cs2.report_date <= t.report_date)) as beneficiary_econ_sector_code ,  \n" +
                            "                          (select r.code_alpha_2 " +
                            "                             from eav_data.ref_country r, " +
                            "                                  eav_data.ctr_subject c " +
                            "                            where r.entity_id = c.ref_country_id " +
                            "                              and c.entity_id = t.sender_id " +
                            "                              and c.report_date = (Select max(report_date) " +
                            "                                                     from eav_data.ctr_subject cs2 " +
                            "                                                    where cs2.entity_id = c.entity_id " +
                            "                                                      and cs2.report_date <= t.report_date)" +
                            "                             and r.report_date = (Select max(rf2.report_date) " +
                            "                                                   from  eav_data.ref_country rf2 " +
                            "                                                   where rf2.entity_id =   r.entity_id " +
                            "                                                     and rf2.report_date <= t.report_date ) ) as sender_country_code , " +
                            "                           (select r.code " +
                            "                             from eav_data.ref_residency r, " +
                            "                                  eav_data.ctr_subject c " +
                            "                            where r.entity_id = c.ref_residency_id" +
                            "                              and c.entity_id = t.sender_id" +
                            "                              and c.report_date = (Select max(report_date)" +
                            "                                                     from eav_data.ctr_subject cs2" +
                            "                                                    where cs2.entity_id = c.entity_id" +
                            "                                                      and cs2.report_date <= t.report_date)) as sender_residency_code ," +
                            "                          (select c.name " +
                            "                             from eav_data.ctr_subject c " +
                            "                            where c.entity_id = t.sender_id" +
                            "                              and c.report_date = (Select max(report_date)" +
                            "                                                     from eav_data.ctr_subject cs2" +
                            "                                                    where cs2.entity_id = c.entity_id" +
                            "                                                      and cs2.report_date <= t.report_date)) as sender_name ,  " +
                            "                          (select c.bin_iin " +
                            "                             from eav_data.ctr_subject c " +
                            "                            where c.entity_id = t.sender_id" +
                            "                              and c.report_date = (Select max(report_date)" +
                            "                                                     from eav_data.ctr_subject cs2" +
                            "                                                    where cs2.entity_id = c.entity_id" +
                            "                                                      and cs2.report_date <= t.report_date)) as sender_biniin ,  " +
                            "                          (select r.code " +
                            "                             from eav_data.ref_econ_sector r, " +
                            "                                  eav_data.ctr_subject c " +
                            "                            where r.entity_id = c.ref_econ_sector_id" +
                            "                              and c.entity_id = t.sender_id" +
                            "                              and c.report_date = (Select max(report_date)" +
                            "                                                     from eav_data.ctr_subject cs2" +
                            "                                                    where cs2.entity_id = c.entity_id" +
                            "                                                      and cs2.report_date <= t.report_date)) as sender_econ_sector_code ," +
                            "                           (select r.code " +
                            "                              from eav_data.ref_curr_trans_ppc r" +
                            "                             where r.entity_id = t.ref_curr_trans_ppc_id" +
                            "                               and r.report_date = (Select max(report_date)" +
                            "                                                      from eav_data.ref_curr_trans_ppc cs2" +
                            "                                                     where cs2.entity_id = r.entity_id" +
                            "                                                       and cs2.report_date <= t.report_date)) as curr_trans_ppc_code ," +
                            "                           (select r.code " +
                            "                              from eav_data.ref_currency r" +
                            "                             where r.entity_id = t.ref_currency_id" +
                            "                               and r.report_date = (Select max(report_date)" +
                            "                                                      from eav_data.ref_currency cs2" +
                            "                                                     where cs2.entity_id = r.entity_id" +
                            "                                                       and cs2.report_date <= t.report_date)) as currency_code ," +
                            "                           (select r.short_name " +
                            "                              from eav_data.ref_currency r" +
                            "                             where r.entity_id = t.ref_currency_id" +
                            "                               and r.report_date = (Select max(report_date)" +
                            "                              from eav_data.ref_currency cs2" +
                            "                             where cs2.entity_id = r.entity_id" +
                            "                               and cs2.report_date <= t.report_date)) as currency_name  " +
                            "                       from eav_data.ctr_transaction t , " +
                            "                            reporter.v_currency_usd_tg cur" +
                            "                      where t.cont_date is not null " +
                           // "                        and t.reference = '55124311901072021' " + // 94846223619988501062021
                           /* "                        and t.reference in(\n" +
                            "'563000701032023','563000501062023','563000801032023','563000301032022',\n" +
                            "'563001701032023','563001501032023','563001401032023','563002201052023',\n" +
                            "'563000601032023','563000501032023','563000201032023','563001101032023',\n" +
                            "'563001601032023','563000401112021','563000901032023','563001201032023') " +*/
                            "                        and t.cont_num is not null" +
                            "                        and t.ref_curr_trans_code_id in (1,2,3,15,8,14,11,6,5,4,17,22,18,13,12,10,25,9,20,7,19,16,24,27,30,21,28,31,23,26,29,34,33,32,42,43,45,46,48,49,50,54,55)" +
                            "                        and t.entity_id = cur.entity_id " +
                            "                        and t.report_date = cur.report_date " +
                            "                        and (t.cont_sum * cur.course_curr /cur.corellation/ cur.course_usd) > 50 " +
                            "                        and t.report_date = (Select max(report_date)" +
                            "                                               from eav_data.ctr_transaction t2" +
                            "                                              where t2.entity_id = t.entity_id" +
                            "                                                and t2.report_date <= t.report_date)" ;
            if (operationId==1) {
                selectSql+=
                        "                        and not exists (select 1 " +
                                "                                          from eav_data.ctr_transaction ctr3 " +
                                "                                         where ctr3.entity_id = t.entity_id " +
                                "                                           and ctr3.creditor_id = t.creditor_id " +
                                "                                           and ctr3.operation_id = 3) " +
                                "                        and t.report_date = :REPORT_DATE " +
                                "                        union all    " +
                                "                        select 'NEW' as message_type, " +
                                "                         t.reference, " +
                                "                         t.system_date correction_date," +
                                "                         t.curr_trans_date, " +
                                "                         t.cont_sum, " +
                                "                         t.cont_num, " +
                                "                         t.cont_date, " +
                                "                         t.cont_reg_num , " +
                                "                         (select r.code_alpha_2 " +
                                "                            from eav_data.ref_country r, " +
                                "                                 eav_data.ctr_subject c " +
                                "                           where r.entity_id = c.ref_country_id " +
                                "                             and c.entity_id = t.beneficiary_id " +
                                "                             and c.report_date = (Select max(report_date) " +
                                "                                                    from eav_data.ctr_subject cs2 " +
                                "                                                   where cs2.entity_id = c.entity_id " +
                                "                                                     and cs2.report_date <= t.report_date)" +
                                "                             and r.report_date = (Select max(rf2.report_date) " +
                                "                                                   from  eav_data.ref_country rf2 " +
                                "                                                   where rf2.entity_id =   r.entity_id " +
                                "                                                     and rf2.report_date <= t.report_date ) ) as beneficiary_country_code , " +
                                "                          (select r.code " +
                                "                             from eav_data.ref_residency r, " +
                                "                                  eav_data.ctr_subject c  " +
                                "                            where r.entity_id = c.ref_residency_id " +
                                "                              and c.entity_id = t.beneficiary_id " +
                                "                              and c.report_date = (Select max(report_date) " +
                                "                                                     from eav_data.ctr_subject cs2 " +
                                "                                                    where cs2.entity_id = c.entity_id " +
                                "                                                      and cs2.report_date <= t.report_date)) as beneficiary_residency_code , " +
                                "                          (select c.name " +
                                "                             from eav_data.ctr_subject c  " +
                                "                            where c.entity_id = t.beneficiary_id " +
                                "                              and c.report_date = (Select max(report_date) " +
                                "                                                     from eav_data.ctr_subject cs2 " +
                                "                                                    where cs2.entity_id = c.entity_id " +
                                "                                                      and cs2.report_date <= t.report_date)) as beneficiary_name ,   " +
                                //"                       (select c.bin_iin_id from eav_data.ctr_subject c \n" +
                                "                          (select c.bin_iin " +
                                "                             from eav_data.ctr_subject c  " +
                                "                            where c.entity_id = t.beneficiary_id " +
                                "                              and c.report_date = (Select max(report_date) " +
                                "                                                     from eav_data.ctr_subject cs2 " +
                                "                                                    where cs2.entity_id = c.entity_id " +
                                "                                                      and cs2.report_date <= t.report_date)) as beneficiary_biniin ,  " +
                                "                          (select r.code " +
                                "                             from eav_data.ref_econ_sector r, " +
                                "                                  eav_data.ctr_subject c  " +
                                "                            where r.entity_id = c.ref_econ_sector_id " +
                                "                             and c.entity_id = t.beneficiary_id " +
                                "                             and c.report_date = (Select max(report_date) " +
                                "                                                    from eav_data.ctr_subject cs2 " +
                                "                                                   where cs2.entity_id = c.entity_id " +
                                "                                                     and cs2.report_date <= t.report_date)) as beneficiary_econ_sector_code ,   " +
                                "                          (select r.code_alpha_2 " +
                                "                             from eav_data.ref_country r, " +
                                "                                  eav_data.ctr_subject c  " +
                                "                            where r.entity_id = c.ref_country_id " +
                                "                              and c.entity_id = t.sender_id " +
                                "                              and c.report_date = (Select max(report_date) " +
                                "                                                     from eav_data.ctr_subject cs2 " +
                                "                                                    where cs2.entity_id = c.entity_id " +
                                "                                                      and cs2.report_date <= t.report_date)" +
                                "                             and r.report_date = (Select max(rf2.report_date) " +
                                "                                                   from  eav_data.ref_country rf2 " +
                                "                                                   where rf2.entity_id =   r.entity_id " +
                                "                                                     and rf2.report_date <= t.report_date )) as sender_country_code , " +
                                "                           (select r.code " +
                                "                             from eav_data.ref_residency r, " +
                                "                                  eav_data.ctr_subject c  " +
                                "                            where r.entity_id = c.ref_residency_id " +
                                "                              and c.entity_id = t.sender_id " +
                                "                              and c.report_date = (Select max(report_date) " +
                                "                                                     from eav_data.ctr_subject cs2 " +
                                "                                                    where cs2.entity_id = c.entity_id " +
                                "                                                      and cs2.report_date <= t.report_date)) as sender_residency_code , " +
                                "                          (select c.name " +
                                "                             from eav_data.ctr_subject c " +
                                "                            where c.entity_id = t.sender_id " +
                                "                              and c.report_date = (Select max(report_date) " +
                                "                                                     from eav_data.ctr_subject cs2 " +
                                "                                                    where cs2.entity_id = c.entity_id " +
                                "                                                      and cs2.report_date <= t.report_date)) as sender_name ,  " +
                                "                          (select c.bin_iin " +
                                "                             from eav_data.ctr_subject c " +
                                "                            where c.entity_id = t.sender_id " +
                                "                              and c.report_date = (Select max(report_date) " +
                                "                                                     from eav_data.ctr_subject cs2 " +
                                "                                                    where cs2.entity_id = c.entity_id " +
                                "                                                      and cs2.report_date <= t.report_date)) as sender_biniin ,  " +
                                "                          (select r.code " +
                                "                             from eav_data.ref_econ_sector r, " +
                                "                                  eav_data.ctr_subject c " +
                                "                            where r.entity_id = c.ref_econ_sector_id " +
                                "                              and c.entity_id = t.sender_id " +
                                "                              and c.report_date = (Select max(report_date) " +
                                "                                                     from eav_data.ctr_subject cs2 " +
                                "                                                    where cs2.entity_id = c.entity_id " +
                                "                                                      and cs2.report_date <= t.report_date)) as sender_econ_sector_code , " +
                                "                           (select r.code " +
                                "                              from eav_data.ref_curr_trans_ppc r " +
                                "                             where r.entity_id = t.ref_curr_trans_ppc_id " +
                                "                               and r.report_date = (Select max(report_date) " +
                                "                                                      from eav_data.ref_curr_trans_ppc cs2 " +
                                "                                                     where cs2.entity_id = r.entity_id " +
                                "                                                       and cs2.report_date <= t.report_date)) as curr_trans_ppc_code , " +
                                "                           (select r.code " +
                                "                              from eav_data.ref_currency r " +
                                "                             where r.entity_id = t.ref_currency_id " +
                                "                               and r.report_date = (Select max(report_date) " +
                                "                                                      from eav_data.ref_currency cs2 " +
                                "                                                     where cs2.entity_id = r.entity_id " +
                                "                                                       and cs2.report_date <= t.report_date)) as currency_code , " +
                                "                           (select r.short_name " +
                                "                              from eav_data.ref_currency r " +
                                "                             where r.entity_id = t.ref_currency_id " +
                                "                               and r.report_date = (Select max(report_date) " +
                                "                              from eav_data.ref_currency cs2 " +
                                "                             where cs2.entity_id = r.entity_id " +
                                "                               and cs2.report_date <= t.report_date)) as currency_name  " +
                                "                       from eav_data.ctr_transaction t , " +
                                "                            reporter.v_currency_usd_tg cur " +
                                "                      where t.cont_date is not null " +
                               /* "                        and t.reference in('563000701032023','563000501062023','563000801032023','563000301032022'," +
                                "                                          '563001701032023','563001701032023','563001501032023','563001401032023','563002201052023'," +
                                "                                           '563000601032023','563000501032023','563000201032023','563001101032023','563001601032023'," +
                                "                                            '563000401112021','563000901032023', '563001201032023') " +*/
                                "                        and t.cont_num is not null " +
                                "                        and t.ref_curr_trans_code_id in (1,2,3,15,8,14,11,6,5,4,17,22,18,13,12,10,25,9,20,7,19,16,24,27,30,21,28,31,23,26,29,34,33,32,42,43,45,46,48,49,50,54,55)\n" +
                                "                        and t.entity_id = cur.entity_id " +
                                "                        and t.report_date = cur.report_date " +
                                "                        and (t.cont_sum * cur.course_curr /cur.corellation/ cur.course_usd) > 50 " +
                                "                        and t.report_date = (Select max(report_date) " +
                                "                                               from eav_data.ctr_transaction t2 " +
                                "                                              where t2.entity_id = t.entity_id " +
                                "                                                and t2.report_date <= t.report_date) " +
                                "                        and not exists (select 1 " +
                                "                                          from eav_data.ctr_transaction ctr3 " +
                                "                                         where ctr3.entity_id = t.entity_id " +
                                "                                           and ctr3.creditor_id = t.creditor_id " +
                                "                                           and ctr3.operation_id = 3) " +
                                "                        and t.report_date < :REPORT_DATE " +
                                "                        and (select min(SYSTEM_DATE) " +
                                "                              from eav_xml.ctr_transaction xt " +
                                "                             where xt.entity_id = t.entity_id " +
                                "                               and xt.creditor_id = t.creditor_id) > add_months(:PERIOD_DATE,-1) " +
                                "                        and (select min(SYSTEM_DATE) " +
                                "                               from eav_xml.ctr_transaction xt " +
                                "                              where xt.entity_id = t.entity_id " +
                                "                                and xt.creditor_id = t.creditor_id) < :PERIOD_DATE";
            }
        }

        if (operationId==2){
            selectSql+=
                    "                       and exists (select 1 " +
                            "                                  from eav_data.ctr_transaction ctr3 " +
                            "                                 where ctr3.entity_id = t.entity_id " +
                            "                                   and ctr3.creditor_id = t.creditor_id " +
                            "                                   and ctr3.operation_id = 2) " ;
        }
          /*if (operationId==3){
             selectSql+=

                              "                       and exists (select 1 " +
                              "                                  from eav_data.ctr_transaction ctr3 " +
                              "                                 where ctr3.entity_id = t.entity_id " +
                              "                                   and ctr3.creditor_id = t.creditor_id " +
                              "                                   and ctr3.operation_id = 3) " ;
          }*/
        if (operationId==2 /*|| operationId==3*/) {
            selectSql +=
                    "                         and t.report_date < :REPORT_DATE " +
                            "                         and t.system_date >= add_months(:PERIOD_DATE,-1)" +
                            "                         and t.system_date < :PERIOD_DATE  ";
            if (operationId==2) {
                selectSql +=
                        "                         and (select min(SYSTEM_DATE) " +
                                "                                from eav_xml.ctr_transaction xt " +
                                "                               where xt.entity_id = t.entity_id " +
                                "                                 and xt.creditor_id = t.creditor_id) < add_months(:PERIOD_DATE,-1) ";
            }

        }
        selectSql+=
                "          ) d                                                                                  " +
                        "            where  ((d.sender_country_code = 'KZ' and d.beneficiary_country_code <> 'KZ') or \n" +
                        "                   (d.sender_country_code <> 'KZ' and d.beneficiary_country_code = 'KZ') or \n" +
                        "                   (d.sender_country_code <> 'KZ' and d.beneficiary_country_code <> 'KZ'))" ;

        return npJdbcTemplate.queryForList(selectSql,new MapSqlParameterSource().addValue("REPORT_DATE", SqlJdbcConverter.convertToSqlDate(reportDate))
                                                                                .addValue("PERIOD_DATE", SqlJdbcConverter.convertToSqlTimestamp(periodDate)));

    }

    @Override
    public void insertRequest(Request request) {
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("REPORT_DATE", SqlJdbcConverter.convertToSqlDate(request.getReportDate()))
                .addValue("SEND_DATE", SqlJdbcConverter.convertToSqlDate(request.getSendDate()))
                .addValue("STATUS_ID",  request.getRequestStatus().getId())
                .addValue("REQUEST_BODY", request.getRequestBody())
                .addValue("RESPONSE_BODY", request.getResponseBody())
                .addValue("ENTITIES_COUNT", request.getEntitiesCount())
                .addValue("REQUEST_ID", request.getRequestId())
                .addValue("OPERATION_ID", request.getOperationId());

        int count = requestInsert.execute(params);
        if (count != 1)
            throw new UsciException("Ошибка insert записи в таблицу USCI_WS.CTR_KGD");

    }

    @Override
    public void updateRequest(Request request) {
        int count = npJdbcTemplate.update("update USCI_WS.CTR_KGD\n" +
                        "   set STATUS_ID = :STATUS_ID, ENTITIES_COUNT = :ENTITIES_COUNT, REQUEST_BODY = :REQUEST_BODY, " +
                        "       RESPONSE_BODY = :RESPONSE_BODY, REQUEST_ID = :REQUEST_ID\n" +
                        " where ID = :ID",
                new MapSqlParameterSource("ID", request.getId())
                        .addValue("STATUS_ID", request.getRequestStatus().getId())
                        .addValue("REQUEST_BODY", request.getRequestBody())
                        .addValue("RESPONSE_BODY", request.getResponseBody())
                        .addValue("ENTITIES_COUNT", request.getEntitiesCount())
                        .addValue("REQUEST_ID", request.getRequestId()));

        if (count != 1)
            throw new UsciException("Ошибка update записи в таблице USCI_WS.CTR_KGD");

    }

    @Override
    public List<Request> getCtrRequestList(LocalDate reportDate) {
        List<Request> requestList = new ArrayList<>();

        String query = "select * from USCI_WS.CTR_KGD\n";
        if (reportDate != null)
            query += " where REPORT_DATE = :REPORT_DATE\n";

        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("REPORT_DATE", SqlJdbcConverter.convertToSqlDate(reportDate));

        List<Map<String, Object>> rows = npJdbcTemplate.queryForList(query, params);
        if (rows.isEmpty())
            return Collections.emptyList();

        for (Map<String, Object> row : rows) {
            Request request = getRequestFromJdbcMap(row);
            requestList.add(request);
        }

        return requestList;
    }

    private Request getRequestFromJdbcMap(Map<String, Object> row) {
        Request request = new Request();
        request.setId(SqlJdbcConverter.convertToLong(row.get("ID")));
        request.setReportDate(SqlJdbcConverter.convertToLocalDate(row.get("REPORT_DATE")));
        request.setSendDate(SqlJdbcConverter.convertToLocalDate(row.get("SEND_DATE")));
        request.setRequestStatus(RequestStatus.getRequestStatus(SqlJdbcConverter.convertToLong(row.get("STATUS_ID"))));
        request.setRequestBody(String.valueOf(row.get("REQUEST_BODY")));
        request.setResponseBody(String.valueOf(row.get("RESPONSE_BODY")));
        request.setEntitiesCount(SqlJdbcConverter.convertToLong(row.get("ENTITIES_COUNT")));
        request.setOperationId(SqlJdbcConverter.convertToInt(row.get("OPERATION_ID")));
        return request;
    }
}

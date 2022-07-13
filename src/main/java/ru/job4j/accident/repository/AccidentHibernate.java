package ru.job4j.accident.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;

import java.util.List;
import java.util.function.Function;

/*
@Repository
 */
public class AccidentHibernate {

    private final SessionFactory sf;

    public AccidentHibernate(SessionFactory sf) {
        this.sf = sf;
    }

    private <T> T tx(final Function<Session, T> command) {
        final Session session = sf.openSession();
        final Transaction tx = session.beginTransaction();
        try {
            T rsl = command.apply(session);
            tx.commit();
            return rsl;
        } catch (final Exception e) {
            session.getTransaction().rollback();
            throw e;
        }
    }

    public Accident save(Accident accident, String[] rIds) {
        this.tx(session ->
                session.save(String.valueOf(accident), rIds)
        );
        return accident;
    }

    public List<Accident> getAll() {
        return this.tx(session ->
                session.createQuery(" SELECT DISTINCT a from Accident "
                                        + " a join fetch a.rules ",
                                Accident.class)
                        .list()
        );
    }

    public Accident update(Accident accident) {
        return this.tx(session -> {
                    session.update(accident);
                    return accident;
                }
        );
    }

    public Accident findByAccidentId(int id) {
        return (Accident) this.tx(session ->
                session.createQuery(" SELECT DISTINCT a from Accident "
                                + " a join fetch a.rules where a.id = :fId")
                        .setParameter("fId", id)
                        .uniqueResult()
        );
    }

    public List<AccidentType> findAllTypes() {
        return this.tx(
                session -> session.createQuery(" from  AccidentType ", AccidentType.class).list()
        );
    }

    public List<Rule> findAllRules() {
        return this.tx(
                session -> session.createQuery(" from  Rule ", Rule.class).list()
        );
    }

    public Rule findRuleById(int id) {
        return this.tx(
                session -> session.get(Rule.class, id)
        );
    }

}
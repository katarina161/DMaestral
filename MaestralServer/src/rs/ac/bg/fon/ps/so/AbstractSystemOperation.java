/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.so;

import rs.ac.bg.fon.ps.repository.Repository;
import rs.ac.bg.fon.ps.repository.db.DbRepository;
import rs.ac.bg.fon.ps.repository.db.impl.RepositoryDbGeneric;

/**
 *
 * @author Katarina
 */
public abstract class AbstractSystemOperation {
    
    protected Repository repository;

    public AbstractSystemOperation() {
        this.repository = new RepositoryDbGeneric();
    }
    
    public final void executeOperation() throws Exception {
        try {
            checkPreconditions();
            startTransaction();
            executeSpecificOperation();
            commitTransaction();
        } catch (Exception e) {
            rollbackTransaction();
            throw e;
        } finally {
            disconnect();
        }
    }

    protected abstract void checkPreconditions() throws Exception;

    private void startTransaction() throws Exception {
        ((DbRepository) repository).connect();
    }

    protected abstract void executeSpecificOperation() throws Exception;

    private void commitTransaction() throws Exception {
        ((DbRepository) repository).commit();
    }

    private void rollbackTransaction() throws Exception {
        ((DbRepository) repository).rollback();
    }

    private void disconnect() throws Exception {
        ((DbRepository) repository).disconnect();
    }
    
}

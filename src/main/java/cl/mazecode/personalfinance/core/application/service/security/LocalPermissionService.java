package cl.mazecode.personalfinance.core.application.service.security;

import cl.mazecode.personalfinance.core.domain.model.ModelI;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.acls.domain.GrantedAuthoritySid;
import org.springframework.security.acls.domain.ObjectIdentityImpl;
import org.springframework.security.acls.domain.PrincipalSid;
import org.springframework.security.acls.model.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

@Service
@Transactional
public class LocalPermissionService {

    @Autowired
    private MutableAclService aclService;

    @Autowired
    private PlatformTransactionManager transactionManager;

    public void addPermissionForUser(ModelI targetObj, Permission permission, String username) {
        final Sid sid = new PrincipalSid(username);
        addPermissionForSid(targetObj, permission, sid);
    }

    public void addPermissionForAuthority(ModelI targetObj, Permission permission, String authority) {
        final Sid sid = new GrantedAuthoritySid(authority);
        addPermissionForSid(targetObj, permission, sid);
    }

    private void addPermissionForSid(ModelI targetObj, Permission permission, Sid sid) {
        final TransactionTemplate tt = new TransactionTemplate(transactionManager);

        tt.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(@NonNull TransactionStatus status) {
                final ObjectIdentity oi = new ObjectIdentityImpl(targetObj.getClass(), targetObj.getId());

                MutableAcl acl = null;
                try {
                    acl = (MutableAcl) aclService.readAclById(oi);
                } catch (final NotFoundException nfe) {
                    acl = aclService.createAcl(oi);
                }

                acl.insertAce(acl.getEntries()
                                 .size(), permission, sid, true);
                aclService.updateAcl(acl);
            }
        });
    }
}
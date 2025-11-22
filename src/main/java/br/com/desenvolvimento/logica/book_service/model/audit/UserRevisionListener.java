package br.com.desenvolvimento.logica.book_service.model.audit;

import org.hibernate.envers.RevisionListener;
import org.springframework.security.core.context.SecurityContextHolder;

public class UserRevisionListener implements RevisionListener {

    @Override
    public void newRevision(Object revisionEntity) {
        CustomRevisionEntity rev = (CustomRevisionEntity) revisionEntity;

        String username = "SYSTEM";

        try {
            var auth = SecurityContextHolder.getContext().getAuthentication();
            if (auth != null && auth.isAuthenticated()) {
                username = auth.getName();
            }
        } catch (Exception ignored) {}
        rev.setUsername(username);
    }
}

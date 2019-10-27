package com.newtouch.cloud.demo.service.auth.persistence.model;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.util.ClassUtils;
import org.springframework.util.StringUtils;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Slf4j
public class LoginOAuthClientDetails implements ClientDetails {

    private OauthClientDetails oauthClientDetails;

    private JsonMapper mapper = createJsonMapper();

    public LoginOAuthClientDetails(OauthClientDetails oauthClientDetails) {
        this.oauthClientDetails = oauthClientDetails;
    }

    @Override
    public String getClientId() {
        return oauthClientDetails.getClientId();
    }

    @Override
    public Set<String> getResourceIds() {
        if (StringUtils.hasText(oauthClientDetails.getResourceIds())) {
            return StringUtils.commaDelimitedListToSet(oauthClientDetails.getResourceIds());
        }
        return null;
    }

    @Override
    public boolean isSecretRequired() {
        return null != oauthClientDetails.getClientSecret();
    }

    @Override
    public String getClientSecret() {
        return oauthClientDetails.getClientSecret();
    }

    @Override
    public boolean isScoped() {
        return oauthClientDetails.getScope() != null && !oauthClientDetails.getScope().isEmpty();
    }

    @Override
    public Set<String> getScope() {
        if (StringUtils.hasText(oauthClientDetails.getScope())) {
            return StringUtils.commaDelimitedListToSet(oauthClientDetails.getScope());
        }
        return null;
    }

    @Override
    public Set<String> getAuthorizedGrantTypes() {
        if (StringUtils.hasText(oauthClientDetails.getAuthorizedGrantTypes())) {
            return StringUtils.commaDelimitedListToSet(oauthClientDetails.getAuthorizedGrantTypes());
        }
        return null;
    }

    @Override
    public Set<String> getRegisteredRedirectUri() {
        if (StringUtils.hasText(oauthClientDetails.getWebServerRedirectUri())) {
            return StringUtils.commaDelimitedListToSet(oauthClientDetails.getWebServerRedirectUri());
        }
        return null;
    }

    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        if (StringUtils.hasText(oauthClientDetails.getAuthorities())) {
            return AuthorityUtils.commaSeparatedStringToAuthorityList(oauthClientDetails.getAuthorities());
        }
        return null;
    }

    @Override
    public Integer getAccessTokenValiditySeconds() {
        return oauthClientDetails.getAccessTokenValidity();
    }

    @Override
    public Integer getRefreshTokenValiditySeconds() {
        return oauthClientDetails.getRefreshTokenValidity();
    }

    @Override
    public boolean isAutoApprove(String scope) {
        if (oauthClientDetails.getAutoapprove() == null) {
            return false;
        }

        HashSet<String> autoApproveScopes = new HashSet<>(StringUtils.commaDelimitedListToSet(oauthClientDetails.getAutoapprove()));

        for (String auto : autoApproveScopes) {
            if (auto.equals("true") || scope.matches(auto)) {
                return true;
            }
        }
        return false;

    }

    @Override
    public Map<String, Object> getAdditionalInformation() {
        if (null != oauthClientDetails.getAdditionalInformation()) {
            try {

                return mapper.read(oauthClientDetails.getAdditionalInformation(), Map.class);

            }
            catch (Exception e) {
                log.warn("Could not decode JSON for additional information: " + oauthClientDetails.getAdditionalInformation(), e);
            }

        }
        return null;
    }

    interface JsonMapper {
//        String write(Object input) throws Exception;

        <T> T read(String input, Class<T> type) throws Exception;
    }

    private static JsonMapper createJsonMapper() {
        if (ClassUtils.isPresent("org.codehaus.jackson.map.ObjectMapper", null)) {
            return new JacksonMapper();
        }
        else if (ClassUtils.isPresent("com.fasterxml.jackson.databind.ObjectMapper", null)) {
            return new Jackson2Mapper();
        }
        return new NotSupportedJsonMapper();
    }

    private static class JacksonMapper implements JsonMapper {
        private org.codehaus.jackson.map.ObjectMapper mapper = new org.codehaus.jackson.map.ObjectMapper();

//        @Override
//        public String write(Object input) throws Exception {
//            return mapper.writeValueAsString(input);
//        }

        @Override
        public <T> T read(String input, Class<T> type) throws Exception {
            return mapper.readValue(input, type);
        }
    }

    private static class Jackson2Mapper implements JsonMapper {
        private com.fasterxml.jackson.databind.ObjectMapper mapper = new com.fasterxml.jackson.databind.ObjectMapper();

//        @Override
//        public String write(Object input) throws Exception {
//            return mapper.writeValueAsString(input);
//        }

        @Override
        public <T> T read(String input, Class<T> type) throws Exception {
            return mapper.readValue(input, type);
        }
    }

    private static class NotSupportedJsonMapper implements JsonMapper {
//        @Override
//        public String write(Object input) throws Exception {
//            throw new UnsupportedOperationException(
//                    "Neither Jackson 1 nor 2 is available so JSON conversion cannot be done");
//        }

        @Override
        public <T> T read(String input, Class<T> type) throws Exception{
            throw new UnsupportedOperationException(
                    "Neither Jackson 1 nor 2 is available so JSON conversion cannot be done");
        }
    }

}

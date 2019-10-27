package com.newtouch.cloud.demo.service.auth.persistence.mapper;

import com.newtouch.cloud.demo.service.auth.persistence.model.OauthClientDetails;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

@Mapper
public interface OauthClientDetailsMapper {
    @Delete({
        "delete from oauth_client_details",
        "where client_id = #{clientId,jdbcType=VARCHAR}"
    })
    int deleteByPrimaryKey(String clientId);

    @Insert({
        "insert into oauth_client_details (client_id, resource_ids, ",
        "client_secret, scope, ",
        "authorized_grant_types, web_server_redirect_uri, ",
        "authorities, access_token_validity, ",
        "refresh_token_validity, additional_information, ",
        "autoapprove)",
        "values (#{clientId,jdbcType=VARCHAR}, #{resourceIds,jdbcType=VARCHAR}, ",
        "#{clientSecret,jdbcType=VARCHAR}, #{scope,jdbcType=VARCHAR}, ",
        "#{authorizedGrantTypes,jdbcType=VARCHAR}, #{webServerRedirectUri,jdbcType=VARCHAR}, ",
        "#{authorities,jdbcType=VARCHAR}, #{accessTokenValidity,jdbcType=INTEGER}, ",
        "#{refreshTokenValidity,jdbcType=INTEGER}, #{additionalInformation,jdbcType=VARCHAR}, ",
        "#{autoapprove,jdbcType=VARCHAR})"
    })
    int insert(OauthClientDetails record);

    @Select({
        "select",
        "client_id, resource_ids, client_secret, scope, authorized_grant_types, web_server_redirect_uri, ",
        "authorities, access_token_validity, refresh_token_validity, additional_information, ",
        "autoapprove",
        "from oauth_client_details",
        "where client_id = #{clientId,jdbcType=VARCHAR}"
    })
    @Results({
        @Result(column="client_id", property="clientId", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="resource_ids", property="resourceIds", jdbcType=JdbcType.VARCHAR),
        @Result(column="client_secret", property="clientSecret", jdbcType=JdbcType.VARCHAR),
        @Result(column="scope", property="scope", jdbcType=JdbcType.VARCHAR),
        @Result(column="authorized_grant_types", property="authorizedGrantTypes", jdbcType=JdbcType.VARCHAR),
        @Result(column="web_server_redirect_uri", property="webServerRedirectUri", jdbcType=JdbcType.VARCHAR),
        @Result(column="authorities", property="authorities", jdbcType=JdbcType.VARCHAR),
        @Result(column="access_token_validity", property="accessTokenValidity", jdbcType=JdbcType.INTEGER),
        @Result(column="refresh_token_validity", property="refreshTokenValidity", jdbcType=JdbcType.INTEGER),
        @Result(column="additional_information", property="additionalInformation", jdbcType=JdbcType.VARCHAR),
        @Result(column="autoapprove", property="autoapprove", jdbcType=JdbcType.VARCHAR)
    })
    OauthClientDetails selectByPrimaryKey(String clientId);

    @Select({
        "select",
        "client_id, resource_ids, client_secret, scope, authorized_grant_types, web_server_redirect_uri, ",
        "authorities, access_token_validity, refresh_token_validity, additional_information, ",
        "autoapprove",
        "from oauth_client_details",
        "order by client_id"
    })
    @Results({
        @Result(column="client_id", property="clientId", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="resource_ids", property="resourceIds", jdbcType=JdbcType.VARCHAR),
        @Result(column="client_secret", property="clientSecret", jdbcType=JdbcType.VARCHAR),
        @Result(column="scope", property="scope", jdbcType=JdbcType.VARCHAR),
        @Result(column="authorized_grant_types", property="authorizedGrantTypes", jdbcType=JdbcType.VARCHAR),
        @Result(column="web_server_redirect_uri", property="webServerRedirectUri", jdbcType=JdbcType.VARCHAR),
        @Result(column="authorities", property="authorities", jdbcType=JdbcType.VARCHAR),
        @Result(column="access_token_validity", property="accessTokenValidity", jdbcType=JdbcType.INTEGER),
        @Result(column="refresh_token_validity", property="refreshTokenValidity", jdbcType=JdbcType.INTEGER),
        @Result(column="additional_information", property="additionalInformation", jdbcType=JdbcType.VARCHAR),
        @Result(column="autoapprove", property="autoapprove", jdbcType=JdbcType.VARCHAR)
    })
    List<OauthClientDetails> selectAll();

    @Update({
        "update oauth_client_details",
        "set resource_ids = #{resourceIds,jdbcType=VARCHAR},",
          "client_secret = #{clientSecret,jdbcType=VARCHAR},",
          "scope = #{scope,jdbcType=VARCHAR},",
          "authorized_grant_types = #{authorizedGrantTypes,jdbcType=VARCHAR},",
          "web_server_redirect_uri = #{webServerRedirectUri,jdbcType=VARCHAR},",
          "authorities = #{authorities,jdbcType=VARCHAR},",
          "access_token_validity = #{accessTokenValidity,jdbcType=INTEGER},",
          "refresh_token_validity = #{refreshTokenValidity,jdbcType=INTEGER},",
          "additional_information = #{additionalInformation,jdbcType=VARCHAR},",
          "autoapprove = #{autoapprove,jdbcType=VARCHAR}",
        "where client_id = #{clientId,jdbcType=VARCHAR}"
    })
    int updateByPrimaryKey(OauthClientDetails record);
}
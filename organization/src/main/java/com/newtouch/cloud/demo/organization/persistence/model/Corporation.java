package com.newtouch.cloud.demo.organization.persistence.model;

import java.io.Serializable;
import lombok.Data;

@Data
public class Corporation implements Serializable {
    private Integer corpId;

    private String corpName;

    private static final long serialVersionUID = 4861594219892588367L;
}
package com.narwhal.basics.external.sendgrid.dto.endpoint;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.google.common.collect.Lists;
import com.narwhal.basics.core.rest.utils.ToStringUtils;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @author Tomas de Priede
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class MailPersonalizations implements Serializable {

    private List<MailAddress> to;
    private List<MailAddress> cc;
    private List<MailAddress> bcc;


    public MailPersonalizations(MailAddress to) {
        this.to = Lists.newArrayList(to);
    }


    @Override
    public String toString() {
        return ToStringUtils.toString(this);
    }
}

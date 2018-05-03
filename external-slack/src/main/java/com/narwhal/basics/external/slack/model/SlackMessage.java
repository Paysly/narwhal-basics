package com.narwhal.basics.external.slack.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SlackMessage extends Slack implements Serializable {

    private String channel;
    private String text;
}

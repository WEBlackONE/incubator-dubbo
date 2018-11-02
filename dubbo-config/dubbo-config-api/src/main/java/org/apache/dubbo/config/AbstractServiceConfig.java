/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.dubbo.config;

import org.apache.dubbo.common.Constants;
import org.apache.dubbo.config.support.Parameter;
import org.apache.dubbo.rpc.ExporterListener;

import java.util.Arrays;
import java.util.List;

/**
 * AbstractServiceConfig-抽象服务配置类
 *
 * @export
 */
public abstract class AbstractServiceConfig extends AbstractInterfaceConfig {

    private static final long serialVersionUID = 1L;

    // version
    /**
    * 服务版本
     * 建议使用两位数字版本，如：1.0，通常在接口不兼容时版本号才需要升级
    */
    protected String version;

    // group
    /**
    * 服务分组
     * 当一个接口有多个实现，可以用分组区分
    */
    protected String group;

    // whether the service is deprecated
    /**
    * 服务是否废弃
    */
    protected Boolean deprecated;

    // delay service exporting
    /**
    * 延迟注册服务时间(毫秒)
     * 设为-1时，表示延迟到Spring容器初始化完成时暴露服务
    */
    protected Integer delay;

    // whether to export the service
    /**
    * 是否暴露服务
    */
    protected Boolean export;

    // weight
    /**
    * 服务权重
    */
    protected Integer weight;

    // document center
    /**
    * 	服务文档URL
    */
    protected String document;

    // whether to register as a dynamic service or not on register center
    /**
     * 是否在注册中心注册为动态服务
    *注册后将显示后disable状态
     * 需人工启用，并且服务提供者停止时，也不会自动取消册，需人工禁用。
     * 默认为true
     *
    */
    protected Boolean dynamic;

    // whether to use token
    /**
    * 令牌验证
     * 为空表示不开启
     * 如果为true，表示随机生成动态令牌，否则使用静态令牌
     * 令牌的作用是防止消费者绕过注册中心直接访问，保证注册中心的授权功能有效
     * 如果使用点对点调用，需关闭令牌功能
     * 缺省为：false
    */
    protected String token;

    // access log
    /**
     * 访问日志
    * 设为true，将向logger中输出访问日志，也可填写访问日志文件路径，直接把访问日志输出到指定文件
     * 缺省：false
    */
    protected String accesslog;

    /**
    * 使用指定的协议暴露服务
     * 在多协议时使用，值为<dubbo:protocol>的id属性，多个协议ID用逗号分隔
    */
    protected List<ProtocolConfig> protocols;
    // max allowed execute times
    /**
    * 最大允许执行时间
    */
    private Integer executes;
    // whether to register
    /**
    *该协议的服务是否注册到注册中心
    */
    private Boolean register;

    // warm up period
    /**
    * TODO 预热阶段是个什么鬼？
    */
    private Integer warmup;

    // serialization
    /**
    * 协议序列化方式
     * 当协议支持多种序列化方式时使用
     * 比如：dubbo协议的dubbo,hessian2,java,compactedjava，以及http协议的json,xml等
    */
    private String serialization;

    // provider tag
    /**
    *提供者标记
    */
    protected String tag;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        checkKey("version", version);
        this.version = version;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        checkKey("group", group);
        this.group = group;
    }

    public Integer getDelay() {
        return delay;
    }

    public void setDelay(Integer delay) {
        this.delay = delay;
    }

    public Boolean getExport() {
        return export;
    }

    public void setExport(Boolean export) {
        this.export = export;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    @Parameter(escaped = true)
    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        checkName("token", token);
        this.token = token;
    }

    public void setToken(Boolean token) {
        if (token == null) {
            setToken((String) null);
        } else {
            setToken(String.valueOf(token));
        }
    }

    public Boolean isDeprecated() {
        return deprecated;
    }

    public void setDeprecated(Boolean deprecated) {
        this.deprecated = deprecated;
    }

    public Boolean isDynamic() {
        return dynamic;
    }

    public void setDynamic(Boolean dynamic) {
        this.dynamic = dynamic;
    }

    public List<ProtocolConfig> getProtocols() {
        return protocols;
    }

    @SuppressWarnings({"unchecked"})
    public void setProtocols(List<? extends ProtocolConfig> protocols) {
        this.protocols = (List<ProtocolConfig>) protocols;
    }

    public ProtocolConfig getProtocol() {
        return protocols == null || protocols.isEmpty() ? null : protocols.get(0);
    }

    public void setProtocol(ProtocolConfig protocol) {
        this.protocols = Arrays.asList(protocol);
    }

    public String getAccesslog() {
        return accesslog;
    }

    public void setAccesslog(String accesslog) {
        this.accesslog = accesslog;
    }

    public void setAccesslog(Boolean accesslog) {
        if (accesslog == null) {
            setAccesslog((String) null);
        } else {
            setAccesslog(String.valueOf(accesslog));
        }
    }

    public Integer getExecutes() {
        return executes;
    }

    public void setExecutes(Integer executes) {
        this.executes = executes;
    }

    @Override
    @Parameter(key = Constants.SERVICE_FILTER_KEY, append = true)
    public String getFilter() {
        return super.getFilter();
    }

    @Override
    @Parameter(key = Constants.EXPORTER_LISTENER_KEY, append = true)
    public String getListener() {
        return listener;
    }

    @Override
    public void setListener(String listener) {
        checkMultiExtension(ExporterListener.class, "listener", listener);
        this.listener = listener;
    }

    public Boolean isRegister() {
        return register;
    }

    public void setRegister(Boolean register) {
        this.register = register;
    }

    public Integer getWarmup() {
        return warmup;
    }

    public void setWarmup(Integer warmup) {
        this.warmup = warmup;
    }

    public String getSerialization() {
        return serialization;
    }

    public void setSerialization(String serialization) {
        this.serialization = serialization;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}

package com.cherryjava.basesys.system.menu.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import java.util.List;

/**
 * 路由显示信息
 * 必须加上@JsonInclude(Include.NON_NULL)的注解  否则传null值给Vue动态路由渲染时会出错
 *
 * @author hjamm
 */
@JsonInclude(Include.NON_NULL)
public class MetaDTO {
    // 菜单名称（兼容国际化、非国际化，如果用国际化的写法就必须在根目录的locales文件夹下对应添加）
    private String title;
    // 菜单图标
    private String icon;
    // 是否显示该菜单
    private Boolean showLink;
    // 是否显示父级菜单
    private Boolean showParent;
    // 页面级别权限设置
    private List<String> roles;
    // 按钮级别权限设置
    private List<String> auths;
    // 需要内嵌的iframe链接地址
    private String frameSrc;
    /**
     * 是否是内部页面   使用frameSrc来嵌入页面时，当isFrameSrcInternal=true的时候, 前端需要做特殊处理
     * 比如链接是 /druid/login.html
     * 前端需要处理成 http://localhost:8080/druid/login.html
     */
    private Boolean isFrameSrcInternal;

    /**
     * 菜单排序，值越高排的越后（只针对顶级路由）
     */
    private Integer rank;


    // =========  目前系统仅支持以上这些参数的设置 后续有需要的话开发者可自行设置的这些参数  ===========

    // 菜单名称右侧的额外图标
    private ExtraIconDTO extraIcon;
    // 是否缓存该路由页面（开启后，会保存该页面的整体状态，刷新后会清空状态）
    private Boolean keepAlive;
    // 内嵌的iframe页面是否开启首次加载动画
    private Boolean frameLoading;
    // 页面加载动画（两种模式，第一种直接采用vue内置的transitions动画，第二种是使用animate.css编写进、离场动画，平台更推荐使用第二种模式，已经内置了animate.css，直接写对应的动画名即可）
    private TransitionDTO transition;
    // 当前菜单名称或自定义信息禁止添加到标签页
    private Boolean hiddenTag;
    // 显示在标签页的最大数量，需满足后面的条件：不显示在菜单中的路由并且是通过query或params传参模式打开的页面。在完整版全局搜dynamicLevel即可查看代码演示
    private Integer dynamicLevel;

    public MetaDTO() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Boolean getShowLink() {
        return showLink;
    }

    public void setShowLink(Boolean showLink) {
        this.showLink = showLink;
    }

    public Boolean getShowParent() {
        return showParent;
    }

    public void setShowParent(Boolean showParent) {
        this.showParent = showParent;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public List<String> getAuths() {
        return auths;
    }

    public void setAuths(List<String> auths) {
        this.auths = auths;
    }

    public String getFrameSrc() {
        return frameSrc;
    }

    public void setFrameSrc(String frameSrc) {
        this.frameSrc = frameSrc;
    }

    public Boolean getFrameSrcInternal() {
        return isFrameSrcInternal;
    }

    public void setFrameSrcInternal(Boolean frameSrcInternal) {
        isFrameSrcInternal = frameSrcInternal;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public ExtraIconDTO getExtraIcon() {
        return extraIcon;
    }

    public void setExtraIcon(ExtraIconDTO extraIcon) {
        this.extraIcon = extraIcon;
    }

    public Boolean getKeepAlive() {
        return keepAlive;
    }

    public void setKeepAlive(Boolean keepAlive) {
        this.keepAlive = keepAlive;
    }

    public Boolean getFrameLoading() {
        return frameLoading;
    }

    public void setFrameLoading(Boolean frameLoading) {
        this.frameLoading = frameLoading;
    }

    public TransitionDTO getTransition() {
        return transition;
    }

    public void setTransition(TransitionDTO transition) {
        this.transition = transition;
    }

    public Boolean getHiddenTag() {
        return hiddenTag;
    }

    public void setHiddenTag(Boolean hiddenTag) {
        this.hiddenTag = hiddenTag;
    }

    public Integer getDynamicLevel() {
        return dynamicLevel;
    }

    public void setDynamicLevel(Integer dynamicLevel) {
        this.dynamicLevel = dynamicLevel;
    }
}

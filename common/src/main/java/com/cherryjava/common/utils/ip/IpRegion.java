package com.cherryjava.common.utils.ip;

import cn.hutool.core.text.CharSequenceUtil;

/**
 * @author hjamm
 */
public class IpRegion {
    private static final String UNKNOWN = "未知";
    private String country;
    private String region;
    private String province;
    private String city;
    private String isp;

    public IpRegion() {
    }

    public IpRegion(String country, String region, String province, String city, String isp) {
        this.country = country;
        this.region = region;
        this.province = province;
        this.city = city;
        this.isp = isp;
    }

    public IpRegion(String province, String city) {
        this.province = province;
        this.city = city;
    }

    public String briefLocation() {
        return String.format("%s %s",
                CharSequenceUtil.nullToDefault(province, UNKNOWN),
                CharSequenceUtil.nullToDefault(city, UNKNOWN)).trim();
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getIsp() {
        return isp;
    }

    public void setIsp(String isp) {
        this.isp = isp;
    }

    @Override
    public String toString() {
        return "IpRegion{" +
                "country='" + country + '\'' +
                ", region='" + region + '\'' +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", isp='" + isp + '\'' +
                '}';
    }
}

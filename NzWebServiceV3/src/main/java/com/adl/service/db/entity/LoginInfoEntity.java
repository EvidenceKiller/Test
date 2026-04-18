package com.adl.service.db.entity;

import com.adl.service.data.LoginInfoData;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity(tableName = "_login_info")
public final class LoginInfoEntity {

    @PrimaryKey(autoGenerate = true)
    private long lid;

    /**
     * 上级机构ID
     */
    @ColumnInfo(name = "_pid")
    private String pid;

    /**
     * 一级机构名称
     */
    @ColumnInfo(name = "_org_name")
    private String orgName;

    /**
     * 机构代码
     */
    @ColumnInfo(name = "_org_code")
    private String orgCode;

    /**
     * 机构类型
     */
    @ColumnInfo(name = "_org_type")
    private Integer orgType;

    /**
     * 区域代码
     */
    @ColumnInfo(name = "_area_codes")
    private String areaCodes;

    /**
     * 区域名称
     */
    @ColumnInfo(name = "_area_names")
    private String areaNames;

    /**
     * 详细地址
     */
    @ColumnInfo(name = "_address")
    private String address;

    /**
     * 管理员名称
     */
    @ColumnInfo(name = "_account_name")
    private String accountName;

    /**
     * 管理员账号
     */
    @ColumnInfo(name = "_account_num")
    private String accountNum;

    /**
     * 机构LOGO
     */
    @ColumnInfo(name = "_orglogo")
    private String orglogo;

    /**
     * 联系方式
     */
    @ColumnInfo(name = "_tel")
    private String tel;

    /**
     * 邮箱
     */
    @ColumnInfo(name = "_email")
    private String email;

    /**
     * 机构ID
     */
    @ColumnInfo(name = "_org_id")
    private String orgId;

    /**
     * 学制代码
     * 参考字典编码: adl_tis_academic_system_code
     */
    @ColumnInfo(name = "_edu_code")
    private String eduCode;

    /**
     * 管理员名称
     */
    @ColumnInfo(name = "_platform_name")
    private String platformName;

    /**
     * 经度
     */
    @ColumnInfo(name = "_longitude")
    private String longitude;

    /**
     * 纬度
     */
    @ColumnInfo(name = "_latitude")
    private String latitude;

    /**
     * 机构设备通用管理密码
     */
    @ColumnInfo(name = "_device_common_password")
    private String deviceCommonPassword;

    /**
     * 启用标识
     * 参考字典编码: adl_tis_enabled
     */
    @ColumnInfo(name = "_enabled")
    private Boolean enabled;

    /**
     * 机构资源文件上传总大小限制
     */
    @ColumnInfo(name = "_file_size_limit")
    private Long fileSizeLimit;

    @ColumnInfo(name = "_porg_name")
    private String porgName;

    public static LoginInfoEntity convertToEntity(LoginInfoData data) {
        if (data == null) {
            return null;
        }
        LoginInfoEntity entity = new LoginInfoEntity();
        entity.setPid(data.getPid());
        entity.setOrgName(data.getOrgName());
        entity.setOrgCode(data.getOrgCode());
        entity.setOrgType(data.getOrgType());
        entity.setAreaCodes(data.getAreaCodes());
        entity.setAreaNames(data.getAreaNames());
        entity.setAddress(data.getAddress());
        entity.setAccountName(data.getAccountName());
        entity.setAccountNum(data.getAccountNum());
        entity.setOrglogo(data.getOrglogo());
        entity.setTel(data.getTel());
        entity.setEmail(data.getEmail());
        entity.setOrgId(data.getOrgId());
        entity.setEduCode(data.getEduCode());
        entity.setPlatformName(data.getPlatformName());
        entity.setLongitude(data.getLongitude());
        entity.setLatitude(data.getLatitude());
        entity.setDeviceCommonPassword(data.getDeviceCommonPassword());
        entity.setEnabled(data.getEnabled());
        entity.setFileSizeLimit(data.getFileSizeLimit());
        entity.setPorgName(data.getPorgName());
        return entity;
    }

    public static LoginInfoData convertToData(LoginInfoEntity entity) {
        if (entity == null) {
            return null;
        }
        LoginInfoData data = new LoginInfoData();
        data.setPid(entity.getPid());
        data.setOrgName(entity.getOrgName());
        data.setOrgCode(entity.getOrgCode());
        data.setOrgType(entity.getOrgType());
        data.setAreaCodes(entity.getAreaCodes());
        data.setAreaNames(entity.getAreaNames());
        data.setAddress(entity.getAddress());
        data.setAccountName(entity.getAccountName());
        data.setAccountNum(entity.getAccountNum());
        data.setOrglogo(entity.getOrglogo());
        data.setTel(entity.getTel());
        data.setEmail(entity.getEmail());
        data.setOrgId(entity.getOrgId());
        data.setEduCode(entity.getEduCode());
        data.setPlatformName(entity.getPlatformName());
        data.setLongitude(entity.getLongitude());
        data.setLatitude(entity.getLatitude());
        data.setDeviceCommonPassword(entity.getDeviceCommonPassword());
        data.setEnabled(entity.getEnabled());
        data.setFileSizeLimit(entity.getFileSizeLimit());
        data.setPorgName(entity.getPorgName());
        return data;
    }
}

package com.baizhi.cmfz.entity;

import java.io.Serializable;
import java.util.List;

/**
 * (CmfzMenu)实体类
 *
 * @author makejava
 * @since 2019-01-03 19:30:15
 */
public class Menu implements Serializable {
    private static final long serialVersionUID = 878358304113670091L;
    
    private Integer menuId;
    
    private String menuName;
    
    private String menuUrl;
    
    private Integer menuParentId;

    private List<Menu> menuList;


    public Integer getMenuId() {
        return menuId;
    }

    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getMenuUrl() {
        return menuUrl;
    }

    public void setMenuUrl(String menuUrl) {
        this.menuUrl = menuUrl;
    }

    public Integer getMenuParentId() {
        return menuParentId;
    }

    public void setMenuParentId(Integer menuParentId) {
        this.menuParentId = menuParentId;
    }

    public List<Menu> getMenuList() {
        return menuList;
    }

    public void setMenuList(List<Menu> menuList) {
        this.menuList = menuList;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "menuId=" + menuId +
                ", menuName='" + menuName + '\'' +
                ", menuUrl='" + menuUrl + '\'' +
                ", menuParentId=" + menuParentId +
                ", menuList=" + menuList +
                '}';
    }
}
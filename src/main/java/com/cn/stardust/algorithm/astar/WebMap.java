package com.cn.stardust.algorithm.astar;

import java.util.List;

/**
 * <p>
 * <p>
 * 地图
 *
 * @author stardust
 * @version 1.0.0
 * 2020/1/2  11:28
 */
public class WebMap {

    /**
     * 地图宽
     */
    private int width;

    /**
     * 地图高
     */
    private int height;

    /**
     * 起点
     */
    private WebPoint start;

    /**
     * 终点
     */
    private WebPoint end;


    /**
     * 地形障碍点
     */
    private List<WebPoint> blockPoints;

    public WebMap(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public WebMap() {
    }

    public int getWidth() {
        return width;
    }

    public WebMap setWidth(int width) {
        this.width = width;
        return this;
    }

    public int getHeight() {
        return height;
    }

    public WebMap setHeight(int height) {
        this.height = height;
        return this;
    }

    public List<WebPoint> getBlockPoints() {
        return blockPoints;
    }

    public void setBlockPoints(List<WebPoint> blockPoints) {
        this.blockPoints = blockPoints;
    }

    public WebPoint getStart() {
        return start;
    }

    public void setStart(WebPoint start) {
        this.start = start;
    }

    public WebPoint getEnd() {
        return end;
    }

    public void setEnd(WebPoint end) {
        this.end = end;
    }
}
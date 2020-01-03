package com.cn.stardust.algorithm.astar;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *
 * @author stardust
 * @version 1.0.0
 * 2020/1/2  9:59
 */

public class WebPoint implements Comparable<WebPoint> {

    /**
     * x 坐标
     */
    private Integer xPos;

    /**
     * y 坐标
     */
    private Integer yPos;

    /**
     * 父级引用
     */
    private WebPoint parent;

    /**
     * 步数
     */
    private int steps;

    /**
     * 距离
     */
    private int distance;

    /**
     * 参考总数值
     */
    private int value;

    public WebPoint() {
    }

    public WebPoint(Integer xPos, Integer yPos) {
        this.xPos = xPos;
        this.yPos = yPos;
    }

    public WebPoint(Integer xPos, Integer yPos, WebPoint currentPoint, int step) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.parent = currentPoint;
        this.steps = currentPoint.getSteps() + step;
    }

    /**
     * 获取所有可行网点
     *
     * @param currentPoint
     * @param bolckPoints
     * @return
     */
    public static List<WebPoint> getPoints(WebPoint currentPoint, List<WebPoint> bolckPoints, int width, int height) {
        List<WebPoint> webPoints = new ArrayList<>(4);
        int step1 = 10;
        int step2 = 14;

        WebPoint leftUp = new WebPoint(currentPoint.getxPos() - 1, currentPoint.getyPos() - 1, currentPoint, step2);
        WebPoint rightUp = new WebPoint(currentPoint.getxPos() + 1, currentPoint.getyPos() - 1, currentPoint, step2);
        WebPoint leftDown = new WebPoint(currentPoint.getxPos() - 1, currentPoint.getyPos() + 1, currentPoint, step2);
        WebPoint rightDown = new WebPoint(currentPoint.getxPos() + 1, currentPoint.getyPos() + 1, currentPoint, step2);
        WebPoint up = new WebPoint(currentPoint.getxPos() + 0, currentPoint.getyPos() - 1, currentPoint, step1);
        WebPoint down = new WebPoint(currentPoint.getxPos() + 0, currentPoint.getyPos() + 1, currentPoint, step1);
        WebPoint left = new WebPoint(currentPoint.getxPos() - 1, currentPoint.getyPos() + 0, currentPoint, step1);
        WebPoint right = new WebPoint(currentPoint.getxPos() + 1, currentPoint.getyPos() + 0, currentPoint, step1);

        webPoints.add(up);
        webPoints.add(down);
        webPoints.add(left);
        webPoints.add(right);
        webPoints.add(leftUp);
        webPoints.add(rightUp);
        webPoints.add(leftDown);
        webPoints.add(rightDown);

        /**
         * 地形阻隔
         */
        if (bolckPoints.contains(up) || currentPoint.getyPos() == 0) {
            webPoints.remove(up);
            webPoints.remove(leftUp);
            webPoints.remove(rightUp);
        }
        if (bolckPoints.contains(down) || currentPoint.getyPos() + 1 == height) {
            webPoints.remove(down);
            webPoints.remove(leftDown);
            webPoints.remove(rightDown);
        }
        if (bolckPoints.contains(left) || currentPoint.getxPos() == 0) {
            webPoints.remove(left);
            webPoints.remove(leftUp);
            webPoints.remove(leftDown);
        }
        if (bolckPoints.contains(right) || currentPoint.getxPos() + 1 == width) {
            webPoints.remove(right);
            webPoints.remove(rightUp);
            webPoints.remove(rightDown);
        }

        if (bolckPoints.contains(leftUp)) {
            webPoints.remove(leftUp);
        }
        if (bolckPoints.contains(leftDown)) {
            webPoints.remove(leftDown);
        }
        if (bolckPoints.contains(rightUp)) {
            webPoints.remove(rightUp);
        }
        if (bolckPoints.contains(rightDown)) {
            webPoints.remove(rightDown);
        }

        return webPoints;
    }


    public static void calculate(List<WebPoint> points, WebPoint destinyPoint) {
        points.forEach(e -> {
            e.setDistance(Math.abs(destinyPoint.getxPos() - e.getxPos()) + Math.abs(destinyPoint.getyPos() - e.getyPos()));
            e.setValue(e.getDistance() + e.getSteps());
        });
    }

    public int getValue() {
        return value;
    }

    public WebPoint setValue(int value) {
        this.value = value;
        return this;
    }

    public Integer getxPos() {
        return xPos;
    }

    public WebPoint setxPos(Integer xPos) {
        this.xPos = xPos;
        return this;
    }

    public Integer getyPos() {
        return yPos;
    }

    public WebPoint setyPos(Integer yPos) {
        this.yPos = yPos;
        return this;
    }

    public WebPoint getParent() {
        return parent;
    }

    public WebPoint setParent(WebPoint parent) {
        this.parent = parent;
        return this;
    }

    public int getSteps() {
        return steps;
    }

    public WebPoint setSteps(int steps) {
        this.steps = steps;
        return this;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    @Override
    public int hashCode() {
        return (this.getyPos() + "" + this.getyPos()).hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof WebPoint) {
            WebPoint des = (WebPoint) obj;
            return this.getxPos().equals(des.getxPos()) && this.getyPos().equals(des.getyPos());
        } else {
            return false;
        }
    }

    @Override
    public int compareTo(WebPoint o) {
        return this.getValue() - o.getValue();
    }
}

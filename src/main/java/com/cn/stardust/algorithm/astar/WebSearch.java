package com.cn.stardust.algorithm.astar;

import com.google.common.collect.Lists;

import java.util.*;

/**
 * https://github.com/oraclexing
 * <p>
 *
 * 自动寻路A*算法 ,示例程序
 * @author stardust
 * @version 1.0.0
 * 2020/1/2  12:04
 */
public class WebSearch {

    /**
     * 启动示例
     * @param args
     */
    public static void main(String[] args) {
        WebSearch webSearch = new WebSearch();
        WebMap webMap = webSearch.initMap();
        WebPoint start = webMap.getStart();
        WebPoint end = webMap.getEnd();
        List<WebPoint> exploredPoints = Lists.newArrayList(start);
        HashMap<String,WebPoint> pointMap = new HashMap<>(100);
        /**
         * 结果优先用map匹配
         */
        WebPoint point = webSearch.search(start, end, webMap.getBlockPoints(), exploredPoints, pointMap, 10, 10);
        WebPoint result = pointMap.get(end.getxPos()+"_"+end.getyPos());
        result = null == result? point:result;
        List<WebPoint> points = new ArrayList<>();
        webSearch.getWebPoint(result,points);
        points.forEach(e -> System.out.println(e.getxPos()+"--"+e.getyPos()));
    }

    /**
     * 开始递归方式搜索
     * @param currentPoint 当前节点
     * @param desPoint 目标节点
     * @param blockPoints 地图障碍节点
     * @param exploredPoints 探索节点
     * @param webPointHashMap 计算过的最优路径点
     * @param width 地图宽
     * @param height 地图高
     * @return
     */
    public WebPoint search(WebPoint currentPoint, WebPoint desPoint, List<WebPoint> blockPoints, List<WebPoint> exploredPoints, HashMap<String, WebPoint> webPointHashMap, int width, int height) {
        List<WebPoint> points = WebPoint.getPoints(currentPoint, blockPoints, width, height);
        Iterator<WebPoint> pointIterator = points.iterator();
        while (pointIterator.hasNext()) {
            // 移除已经探索区域
            WebPoint point = pointIterator.next();
            if (exploredPoints.contains(point)) {
                pointIterator.remove();
            }
        }
        if (points.contains(desPoint)) {
            desPoint.setParent(currentPoint);
            return desPoint;
        }
        WebPoint.calculate(points, desPoint);
        Collections.sort(points);

        points.forEach(e -> {
            if (null == webPointHashMap.get(e.getxPos() + "_" + e.getyPos())) {
                webPointHashMap.put(e.getxPos() + "_" + e.getyPos(), e);
            } else {
                WebPoint p = webPointHashMap.get(e.getxPos() + "_" + e.getyPos());
                if (p.getValue() > e.getValue()) {
                    webPointHashMap.put(e.getxPos() + "_" + e.getyPos(), e);
                }
            }
        });

        if(points.size() == 0){
            return null;
        }
        WebPoint result ;
        for (WebPoint point : points) {
            exploredPoints.add(point);
            WebPoint p = webPointHashMap.get(point.getxPos()+"_"+point.getyPos());
            result = search(p, desPoint, blockPoints, exploredPoints, webPointHashMap, width, height);
            if (null == result) {
                continue;
            } else {
                return result;
            }
        }
        return null;
    }

    /**
     * 初始化地图数据
     *
     * @return
     */
    public WebMap initMap() {
        WebMap webMap = new WebMap(10, 10);
        List<WebPoint> blockPoints = new ArrayList<>();
        blockPoints.add(new WebPoint(0,4));
        blockPoints.add(new WebPoint(1, 6));
        blockPoints.add(new WebPoint(1, 2));
        blockPoints.add(new WebPoint(2, 0));
        blockPoints.add(new WebPoint(2, 6));
        blockPoints.add(new WebPoint(1, 8));
        blockPoints.add(new WebPoint(2, 8));
        blockPoints.add(new WebPoint(3, 5));
        blockPoints.add(new WebPoint(3, 9));
        blockPoints.add(new WebPoint(4, 1));
        blockPoints.add(new WebPoint(4, 2));
        blockPoints.add(new WebPoint(4, 3));
        blockPoints.add(new WebPoint(4, 4));
        blockPoints.add(new WebPoint(4, 5));
        blockPoints.add(new WebPoint(5, 3));
        blockPoints.add(new WebPoint(5, 7));
        blockPoints.add(new WebPoint(5, 8));

        blockPoints.add(new WebPoint(6, 1));
        blockPoints.add(new WebPoint(6, 5));
        blockPoints.add(new WebPoint(7, 2));
        blockPoints.add(new WebPoint(7, 3));
        blockPoints.add(new WebPoint(7, 6));
        blockPoints.add(new WebPoint(7, 8));
        blockPoints.add(new WebPoint(8, 6));
        blockPoints.add(new WebPoint(9, 4));
        blockPoints.add(new WebPoint(9, 9));

        webMap.setBlockPoints(blockPoints);
        webMap.setStart(new WebPoint(2, 3));
        webMap.setEnd(new WebPoint(7, 7));
        return webMap;
    }

    /**
     * 递归正序获取路径
     * @param webPoint
     * @param webPoints
     */
    public void getWebPoint(WebPoint webPoint,List<WebPoint> webPoints){
        if(null != webPoint.getParent()){
            getWebPoint(webPoint.getParent(),webPoints);
            webPoints.add(webPoint);
        }else{
            webPoints.add(webPoint);
        }
    }
}
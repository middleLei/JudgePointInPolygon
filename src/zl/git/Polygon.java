package zl.git;

import java.util.ArrayList;
import java.util.List;

/**
 * 获取多边形
 * 
 * @author zl
 * 
 */
public class Polygon {
	/**
	 * 真实的坐标数据，请查看“测试坐标位置数据\坐标.txt”，其对应的真实位置如“坐标图.png”所示，使用84坐标系（谷歌地图）
	 */
	private final String pointsStr = "116.30859,44.51562,116.30859,44.51562,116.30859,44.51562,112.60546,44.10937,112.10546,38.5,112.9414,43.72656,115.57421,41.5,112.98046,42.75781,112.91015,38.91406,113.42578,42.10937,114.88671,39.28906,114.0039,41.80468,115.67578,38.52343,120.2539,38.60937,114.83203,40.75781,120.03515,39.03125,120.17578,44.84375,116.79296,41.46093,119.83984,39.90625,119.2539,39.53906,116.05078,40.85156,119.5664,44.70312,115.88671,40.88281,115.12109,43.39843,115.02734,42.29687,113.62109,43.94531,116.30859,44.51562";

	/**
	 * 获取多边形方法，返回多边形List
	 * 
	 * @param isColse
	 *            是否闭合，如果true，那么返回数据的最后一个点同第一个点相同，用来验证方法的正确性能
	 * @return
	 */
	public List<Point> getPolygon(boolean isColse) {
		List<Point> polygon = new ArrayList<>();// 多边形数组
		Point p = null;// 每一个点
		String[] pointArr = pointsStr.split(",");// 坐标成双出现
		for (int i = 0; i < pointArr.length; i = i + 2) {// 遍历
			p = new Point();
			p.setX(Double.parseDouble(pointArr[i]));// 经度
			p.setY(Double.parseDouble(pointArr[i + 1]));// 变量i自增后，纬度
			polygon.add(p);
		}
		if (isColse) {// 处理闭合问题
			polygon.add(p);// 增加最后一个点，形成最后一个点的重复数据
			p = polygon.get(0);// 获得起点
			polygon.add(p);// 增加起点，形成起点的重复数据
		}
		return polygon;
	}

}

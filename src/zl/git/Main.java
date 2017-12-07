package zl.git;

import java.util.List;
import java.util.logging.Logger;

public class Main {

	private static Logger log;// 日志系统
	static {// 初始化日志为本地配置文件，否则条用jre/lib下的配置文件
		System.setProperty("java.util.logging.config.file",
				"logging.properties");
		log = Logger.getAnonymousLogger();
	}

	private Main() {
	}

	/**
	 * 主测试方法
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		List<Point> polygon = new Polygon().getPolygon(true);// 获得多边形的点List
		GeneratePoints ponitFactory = new GeneratePoints();
		List<Point> testPointList = ponitFactory.getTestPointList(polygon);// 获取测试点集合
		boolean judgeResult;
		for (Point point : testPointList) {// 输出结果
			judgeResult = Algorithm.isPointInPolygon(point, polygon);
			if (judgeResult)
				log.info("__true:" + point.getInfo());
			else
				log.info("false:" + point.getInfo());// 突出显不在的，易找
		}
	}

}

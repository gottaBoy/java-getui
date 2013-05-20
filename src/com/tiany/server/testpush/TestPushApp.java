package com.tiany.server.testpush;

import java.util.ArrayList;
import java.util.List;
import com.gexin.rp.sdk.base.IIGtPush;
import com.gexin.rp.sdk.base.IPushResult;
import com.gexin.rp.sdk.base.impl.AppMessage;
import com.gexin.rp.sdk.base.impl.ListMessage;
import com.gexin.rp.sdk.base.impl.SingleMessage;
import com.gexin.rp.sdk.base.impl.Target;
import com.gexin.rp.sdk.http.IGtPush;
import com.gexin.rp.sdk.template.LinkTemplate;
import com.gexin.rp.sdk.template.NotificationTemplate;
import com.gexin.rp.sdk.template.TransmissionTemplate;

public class TestPushApp {

	private static final String APPID = "KGFsGWP8zy5Nrr66duVTqA";
	private static final String APPKEY = "JLc63el6i99jwYSVdeXVHA";
	private static final String MASTERSECRET = "oeZrhLNFHk6PkTEcVSCzo4";
	private static final String API = "http://sdk.open.api.igexin.com/apiex.htm"; // OpenService接口地址

	private static IIGtPush mPush;

	private static final int TYPE_SINGLE = 1;
	private static final int TYPE_LIST = 2;
	private static final int TYPE_APP = 3;

	static {
		mPush = new IGtPush(API, APPKEY, MASTERSECRET);
	}

	public static void main(String[] args) {

		int push_type = 0;

		// for SINGLE
		String single_content = null, single_client_id = null;

		// for LIST
		String list_content = null;
		List<String> client_ids = new ArrayList<String>();

		// for APP
		String title = null, content_data = null, logo = null, url = null;

		System.out.print("args: ");
		for (String s : args) {
			System.out.print(" " + s);
		}
		System.out.println();

		if (args.length > 1) {
			push_type = Integer.parseInt(args[0]);

			switch (push_type) {
			case TYPE_SINGLE:
				if (args.length != 3) {
					System.err.println("error param.");
					System.exit(-1);
				}
				single_content = args[1];
				single_client_id = args[2];
				pushToSingle(single_content, single_client_id);
				break;
			case TYPE_LIST:
				if (args.length < 3) {
					System.err.println("error param.");
					System.exit(-1);
				}
				list_content = args[1];
				for (int i = 2; i < args.length; ++i) {
					client_ids.add(args[i]);
				}
				pushToList(list_content, client_ids);
				break;
			case TYPE_APP:
			default:
				if (args.length != 5) {
					System.err.println("error param.");
					System.exit(-1);
				}
				title = args[1];
				content_data = args[2];
				logo = args[3];
				url = args[4];
				pushToApp(title, content_data, logo, url);
			}
			// title = args[1];
			//
			// System.out.println(title);
			// content_data = args[2];
			// logo = args[3];
			// url = args[4];
			// System.out.println(title + " " + content_data + " " + logo + " "
			// + url);
		}

		// 推送主类

		// pushToApp(title, content_data, logo, url);
		// try {
		// AppMessage message = new AppMessage();
		// //
		// 通知模版：支持TransmissionTemplate、LinkTemplate、NotificationTemplate，此处以LinkTemplate为例
		//
		// LinkTemplate template = new LinkTemplate();
		// template.setAppId(APPID); // 应用APPID
		// template.setAppkey(APPKEY); // 应用APPKEY
		// // 通知属性设置：如通知的标题，内容
		// template.setTitle(title); // 通知标题
		//
		// template.setText(content_data); // 通知内容
		// template.setLogo(logo);
		// // template.setIsRing(true); // 收到通知是否响铃，可选，默认响铃
		// // template.setIsVibrate(true); // 收到通知是否震动，可选，默认振动
		// // template.setIsClearable(true); // 通知是否可清除，可选，默认可清除
		// template.setUrl("www.baidu.com"); //
		// 点击通知后打开的网页地址，你可以设定你希望跳转的网页地址如http: // www.igetui.com
		//
		// message.setData(template);
		// message.setOffline(true); //用户当前不在线时，是否离线存储，可选，默认不存储
		// message.setOfflineExpireTime(72 * 3600 * 1000); //离线有效时间，单位为毫秒，可选
		// List<String> appIdList = new ArrayList<String>();
		// appIdList.add(APPID);
		// List<String> phoneTypeList = new ArrayList<String>();//
		// 通知接收者的手机操作系统类型，可选
		// phoneTypeList.add("ANDROID");
		// // List<String> provinceList = new ArrayList<String>(); //
		// 通知接收者所在省份，可选
		// // provinceList.add("浙江");
		// // provinceList.add("上海");
		// // provinceList.add("北京");
		// message.setAppIdList(appIdList);
		// message.setPhoneTypeList(phoneTypeList);
		// // message.setProvinceList(provinceList);
		// IPushResult ret = push.pushMessageToApp(message);
		// System.out.println(ret.getResponse().toString());
		// } catch (Exception e) {
		// e.printStackTrace();
		// }
	}

	private static void pushToApp(String title, String content_data,
			String logo, String url) {
		try {
			AppMessage message = new AppMessage();
			// 通知模版：支持TransmissionTemplate、LinkTemplate、NotificationTemplate，此处以LinkTemplate为例

			LinkTemplate template = new LinkTemplate();
			template.setAppId(APPID); // 应用APPID
			template.setAppkey(APPKEY); // 应用APPKEY
			// 通知属性设置：如通知的标题，内容
			template.setTitle(title); // 通知标题

			template.setText(content_data); // 通知内容
			template.setLogo(logo);
			// template.setIsRing(true); // 收到通知是否响铃，可选，默认响铃
			// template.setIsVibrate(true); // 收到通知是否震动，可选，默认振动
			// template.setIsClearable(true); // 通知是否可清除，可选，默认可清除
			template.setUrl(url); // 点击通知后打开的网页地址，你可以设定你希望跳转的网页地址如http: //
									// www.igetui.com

			message.setData(template);
			message.setOffline(true); // 用户当前不在线时，是否离线存储，可选，默认不存储
			message.setOfflineExpireTime(72 * 3600 * 1000); // 离线有效时间，单位为毫秒，可选
			List<String> appIdList = new ArrayList<String>();
			appIdList.add(APPID);
			List<String> phoneTypeList = new ArrayList<String>();// 通知接收者的手机操作系统类型，可选
			phoneTypeList.add("ANDROID");
			// List<String> provinceList = new ArrayList<String>(); //
			// 通知接收者所在省份，可选
			// provinceList.add("浙江");
			// provinceList.add("上海");
			// provinceList.add("北京");
			message.setAppIdList(appIdList);
			message.setPhoneTypeList(phoneTypeList);
			// message.setProvinceList(provinceList);
			IPushResult ret = mPush.pushMessageToApp(message);
			System.out.println(ret.getResponse().toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void pushToSingle(String content, String clientId) {
		try {
			// 单推消息类型
			SingleMessage message = new SingleMessage();
			// 通知模版：支持TransmissionTemplate、LinkTemplate、NotificationTemplate，此处以TransmissionTemplate为例
			TransmissionTemplate template = new TransmissionTemplate();
			template.setAppId(APPID);
			template.setAppkey(APPKEY);
			template.setTransmissionContent(content);
			// 收到消息是否立即启动应用，1为立即启动，2则广播等待客户端自启动
			template.setTransmissionType(1);
			message.setData(template);
			// message.setOffline(true); //用户当前不在线时，是否离线存储,可选
			// message.setOfflineExpireTime(72 * 3600 * 1000); //离线有效时间，单位为毫秒，可选
			Target target1 = new Target();
			target1.setAppId(APPID);
			// "70f34aab2fc0441e560c7ad8286aab79"
			target1.setClientId(clientId);
			// 单推
			IPushResult ret = mPush.pushMessageToSingle(message, target1);
			System.out.println(ret.getResponse().toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unused")
	private static void pushToList_notify() {
		try {
			ListMessage message = new ListMessage();
			// 通知模版：支持TransmissionTemplate、LinkTemplate、NotificationTemplate，此处以
			// NotificationTemplate为例
			NotificationTemplate template = new NotificationTemplate();
			template.setAppId(APPID); // 应用APPID
			template.setAppkey(APPKEY); // 应用APPKEY
			// 通知属性设置：如通知的标题，内容
			template.setTitle("此处填写通知标题"); // 通知标题
			template.setText("此处填写通知内容"); // 通知内容
			template.setLogo("push.png"); // 通知图标，需要客户端开发时嵌入
			// template.setIsRing(true); // 收到通知是否响铃，可选，默认响铃
			// template.setIsVibrate(true); // 收到通知是否震动，可选，默认振动
			// template.setIsClearable(true); // 通知是否可清除，可选，默认可清除
			template.setTransmissionType(2); // 收到消息是否立即启动应用，1为立即启动，2则广播等待客户端自启动
			template.setTransmissionContent("你需要透传的内容");// 透传内容（点击通知后SDK将透传内容传给你的客户端，需要客户端做相应开发）
			message.setData(template);
			// message.setOffline(true); //用户当前不在线时，是否离线存储，可选，默认不存储
			// message.setOfflineExpireTime(72 * 3600 * 1000); //离线有效时间，单位为毫秒，可选
			// 接收者
			List<Target> targets = new ArrayList<Target>();
			Target target1 = new Target();
			// Target target2 = new Target(); //如果需要可设置多个接收者
			target1.setAppId(APPID); // 接收者安装的应用的APPID
			target1.setClientId("70f34aab2fc0441e560c7ad8286aab79"); // 接收者的ClientID
			// 如需，可设置多个接收者
			// target2.setAppId(APPID2); //接收者2安装应用的APPID
			// target2.setClientId(CLIENTID2); //接收者2的ClientID
			targets.add(target1);
			// targets.add(target2);
			// 推送前通过该接口申请“ContentID”
			String contentId = mPush.getContentId(message);
			IPushResult ret = mPush.pushMessageToList(contentId, targets);
			System.out.println(ret.getResponse().toString());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static void pushToList(String content, List<String> client_ids) {
		try {
			ListMessage message = new ListMessage();
			// 通知模版：支持TransmissionTemplate、LinkTemplate、NotificationTemplate，此处以
			// NotificationTemplate为例
			NotificationTemplate template = new NotificationTemplate();
			template.setAppId(APPID); // 应用APPID
			template.setAppkey(APPKEY); // 应用APPKEY
			// 通知属性设置：如通知的标题，内容
			// template.setTitle("此处填写通知标题"); // 通知标题
			// template.setText("此处填写通知内容"); // 通知内容
			// template.setLogo("push.png"); // 通知图标，需要客户端开发时嵌入
			// template.setIsRing(true); // 收到通知是否响铃，可选，默认响铃
			// template.setIsVibrate(true); // 收到通知是否震动，可选，默认振动
			// template.setIsClearable(true); // 通知是否可清除，可选，默认可清除
			template.setTransmissionType(2); // 收到消息是否立即启动应用，1为立即启动，2则广播等待客户端自启动
			template.setTransmissionContent(content);// 透传内容（点击通知后SDK将透传内容传给你的客户端，需要客户端做相应开发）
			message.setData(template);
			// message.setOffline(true); //用户当前不在线时，是否离线存储，可选，默认不存储
			// message.setOfflineExpireTime(72 * 3600 * 1000); //离线有效时间，单位为毫秒，可选
			// 接收者
			List<Target> targets = new ArrayList<Target>();

			for (String cid : client_ids) {
				Target target = new Target();
				// Target target2 = new Target(); //如果需要可设置多个接收者
				target.setAppId(APPID); // 接收者安装的应用的APPID
				target.setClientId(cid); // 接收者的ClientID
				// 如需，可设置多个接收者
				// target2.setAppId(APPID2); //接收者2安装应用的APPID
				// target2.setClientId(CLIENTID2); //接收者2的ClientID
				targets.add(target);
			}

			// targets.add(target2);
			// 推送前通过该接口申请“ContentID”
			String contentId = mPush.getContentId(message);
			IPushResult ret = mPush.pushMessageToList(contentId, targets);
			System.out.println(ret.getResponse().toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

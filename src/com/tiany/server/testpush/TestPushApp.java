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
	private static final String API = "http://sdk.open.api.igexin.com/apiex.htm"; // OpenService�ӿڵ�ַ

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

		// ��������

		// pushToApp(title, content_data, logo, url);
		// try {
		// AppMessage message = new AppMessage();
		// //
		// ֪ͨģ�棺֧��TransmissionTemplate��LinkTemplate��NotificationTemplate���˴���LinkTemplateΪ��
		//
		// LinkTemplate template = new LinkTemplate();
		// template.setAppId(APPID); // Ӧ��APPID
		// template.setAppkey(APPKEY); // Ӧ��APPKEY
		// // ֪ͨ�������ã���֪ͨ�ı��⣬����
		// template.setTitle(title); // ֪ͨ����
		//
		// template.setText(content_data); // ֪ͨ����
		// template.setLogo(logo);
		// // template.setIsRing(true); // �յ�֪ͨ�Ƿ����壬��ѡ��Ĭ������
		// // template.setIsVibrate(true); // �յ�֪ͨ�Ƿ��𶯣���ѡ��Ĭ����
		// // template.setIsClearable(true); // ֪ͨ�Ƿ���������ѡ��Ĭ�Ͽ����
		// template.setUrl("www.baidu.com"); //
		// ���֪ͨ��򿪵���ҳ��ַ��������趨��ϣ����ת����ҳ��ַ��http: // www.igetui.com
		//
		// message.setData(template);
		// message.setOffline(true); //�û���ǰ������ʱ���Ƿ����ߴ洢����ѡ��Ĭ�ϲ��洢
		// message.setOfflineExpireTime(72 * 3600 * 1000); //������Чʱ�䣬��λΪ���룬��ѡ
		// List<String> appIdList = new ArrayList<String>();
		// appIdList.add(APPID);
		// List<String> phoneTypeList = new ArrayList<String>();//
		// ֪ͨ�����ߵ��ֻ�����ϵͳ���ͣ���ѡ
		// phoneTypeList.add("ANDROID");
		// // List<String> provinceList = new ArrayList<String>(); //
		// ֪ͨ����������ʡ�ݣ���ѡ
		// // provinceList.add("�㽭");
		// // provinceList.add("�Ϻ�");
		// // provinceList.add("����");
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
			// ֪ͨģ�棺֧��TransmissionTemplate��LinkTemplate��NotificationTemplate���˴���LinkTemplateΪ��

			LinkTemplate template = new LinkTemplate();
			template.setAppId(APPID); // Ӧ��APPID
			template.setAppkey(APPKEY); // Ӧ��APPKEY
			// ֪ͨ�������ã���֪ͨ�ı��⣬����
			template.setTitle(title); // ֪ͨ����

			template.setText(content_data); // ֪ͨ����
			template.setLogo(logo);
			// template.setIsRing(true); // �յ�֪ͨ�Ƿ����壬��ѡ��Ĭ������
			// template.setIsVibrate(true); // �յ�֪ͨ�Ƿ��𶯣���ѡ��Ĭ����
			// template.setIsClearable(true); // ֪ͨ�Ƿ���������ѡ��Ĭ�Ͽ����
			template.setUrl(url); // ���֪ͨ��򿪵���ҳ��ַ��������趨��ϣ����ת����ҳ��ַ��http: //
									// www.igetui.com

			message.setData(template);
			message.setOffline(true); // �û���ǰ������ʱ���Ƿ����ߴ洢����ѡ��Ĭ�ϲ��洢
			message.setOfflineExpireTime(72 * 3600 * 1000); // ������Чʱ�䣬��λΪ���룬��ѡ
			List<String> appIdList = new ArrayList<String>();
			appIdList.add(APPID);
			List<String> phoneTypeList = new ArrayList<String>();// ֪ͨ�����ߵ��ֻ�����ϵͳ���ͣ���ѡ
			phoneTypeList.add("ANDROID");
			// List<String> provinceList = new ArrayList<String>(); //
			// ֪ͨ����������ʡ�ݣ���ѡ
			// provinceList.add("�㽭");
			// provinceList.add("�Ϻ�");
			// provinceList.add("����");
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
			// ������Ϣ����
			SingleMessage message = new SingleMessage();
			// ֪ͨģ�棺֧��TransmissionTemplate��LinkTemplate��NotificationTemplate���˴���TransmissionTemplateΪ��
			TransmissionTemplate template = new TransmissionTemplate();
			template.setAppId(APPID);
			template.setAppkey(APPKEY);
			template.setTransmissionContent(content);
			// �յ���Ϣ�Ƿ���������Ӧ�ã�1Ϊ����������2��㲥�ȴ��ͻ���������
			template.setTransmissionType(1);
			message.setData(template);
			// message.setOffline(true); //�û���ǰ������ʱ���Ƿ����ߴ洢,��ѡ
			// message.setOfflineExpireTime(72 * 3600 * 1000); //������Чʱ�䣬��λΪ���룬��ѡ
			Target target1 = new Target();
			target1.setAppId(APPID);
			// "70f34aab2fc0441e560c7ad8286aab79"
			target1.setClientId(clientId);
			// ����
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
			// ֪ͨģ�棺֧��TransmissionTemplate��LinkTemplate��NotificationTemplate���˴���
			// NotificationTemplateΪ��
			NotificationTemplate template = new NotificationTemplate();
			template.setAppId(APPID); // Ӧ��APPID
			template.setAppkey(APPKEY); // Ӧ��APPKEY
			// ֪ͨ�������ã���֪ͨ�ı��⣬����
			template.setTitle("�˴���д֪ͨ����"); // ֪ͨ����
			template.setText("�˴���д֪ͨ����"); // ֪ͨ����
			template.setLogo("push.png"); // ֪ͨͼ�꣬��Ҫ�ͻ��˿���ʱǶ��
			// template.setIsRing(true); // �յ�֪ͨ�Ƿ����壬��ѡ��Ĭ������
			// template.setIsVibrate(true); // �յ�֪ͨ�Ƿ��𶯣���ѡ��Ĭ����
			// template.setIsClearable(true); // ֪ͨ�Ƿ���������ѡ��Ĭ�Ͽ����
			template.setTransmissionType(2); // �յ���Ϣ�Ƿ���������Ӧ�ã�1Ϊ����������2��㲥�ȴ��ͻ���������
			template.setTransmissionContent("����Ҫ͸��������");// ͸�����ݣ����֪ͨ��SDK��͸�����ݴ�����Ŀͻ��ˣ���Ҫ�ͻ�������Ӧ������
			message.setData(template);
			// message.setOffline(true); //�û���ǰ������ʱ���Ƿ����ߴ洢����ѡ��Ĭ�ϲ��洢
			// message.setOfflineExpireTime(72 * 3600 * 1000); //������Чʱ�䣬��λΪ���룬��ѡ
			// ������
			List<Target> targets = new ArrayList<Target>();
			Target target1 = new Target();
			// Target target2 = new Target(); //�����Ҫ�����ö��������
			target1.setAppId(APPID); // �����߰�װ��Ӧ�õ�APPID
			target1.setClientId("70f34aab2fc0441e560c7ad8286aab79"); // �����ߵ�ClientID
			// ���裬�����ö��������
			// target2.setAppId(APPID2); //������2��װӦ�õ�APPID
			// target2.setClientId(CLIENTID2); //������2��ClientID
			targets.add(target1);
			// targets.add(target2);
			// ����ǰͨ���ýӿ����롰ContentID��
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
			// ֪ͨģ�棺֧��TransmissionTemplate��LinkTemplate��NotificationTemplate���˴���
			// NotificationTemplateΪ��
			NotificationTemplate template = new NotificationTemplate();
			template.setAppId(APPID); // Ӧ��APPID
			template.setAppkey(APPKEY); // Ӧ��APPKEY
			// ֪ͨ�������ã���֪ͨ�ı��⣬����
			// template.setTitle("�˴���д֪ͨ����"); // ֪ͨ����
			// template.setText("�˴���д֪ͨ����"); // ֪ͨ����
			// template.setLogo("push.png"); // ֪ͨͼ�꣬��Ҫ�ͻ��˿���ʱǶ��
			// template.setIsRing(true); // �յ�֪ͨ�Ƿ����壬��ѡ��Ĭ������
			// template.setIsVibrate(true); // �յ�֪ͨ�Ƿ��𶯣���ѡ��Ĭ����
			// template.setIsClearable(true); // ֪ͨ�Ƿ���������ѡ��Ĭ�Ͽ����
			template.setTransmissionType(2); // �յ���Ϣ�Ƿ���������Ӧ�ã�1Ϊ����������2��㲥�ȴ��ͻ���������
			template.setTransmissionContent("����Ҫ͸��������");// ͸�����ݣ����֪ͨ��SDK��͸�����ݴ�����Ŀͻ��ˣ���Ҫ�ͻ�������Ӧ������
			message.setData(template);
			// message.setOffline(true); //�û���ǰ������ʱ���Ƿ����ߴ洢����ѡ��Ĭ�ϲ��洢
			// message.setOfflineExpireTime(72 * 3600 * 1000); //������Чʱ�䣬��λΪ���룬��ѡ
			// ������
			List<Target> targets = new ArrayList<Target>();

			for (String cid : client_ids) {
				Target target = new Target();
				// Target target2 = new Target(); //�����Ҫ�����ö��������
				target.setAppId(APPID); // �����߰�װ��Ӧ�õ�APPID
				target.setClientId(cid); // �����ߵ�ClientID
				// ���裬�����ö��������
				// target2.setAppId(APPID2); //������2��װӦ�õ�APPID
				// target2.setClientId(CLIENTID2); //������2��ClientID
				targets.add(target);
			}

			// targets.add(target2);
			// ����ǰͨ���ýӿ����롰ContentID��
			String contentId = mPush.getContentId(message);
			IPushResult ret = mPush.pushMessageToList(contentId, targets);
			System.out.println(ret.getResponse().toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

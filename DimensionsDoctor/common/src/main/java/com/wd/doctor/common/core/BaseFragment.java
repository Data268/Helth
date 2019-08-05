package com.wd.doctor.common.core;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.android.arouter.launcher.ARouter;
import com.google.gson.Gson;
import com.wd.doctor.common.utils.LogUtils;

import androidx.fragment.app.Fragment;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseFragment extends Fragment {
	public Gson mGson = new Gson();
	public SharedPreferences mShare = BaseApplication.getShare();

	private Unbinder unbinder;
	//public UserInfo LOGIN_USER;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		/*UserInfoDao userInfoDao = DaoMaster.newDevSession(getActivity(),UserInfoDao.TABLENAME).getUserInfoDao();
		List<UserInfo> userInfos = userInfoDao.queryBuilder().where(UserInfoDao.Properties.Status.eq(1)).list();
		LOGIN_USER = userInfos.get(0);//读取第一项*/

		// 每次ViewPager要展示该页面时，均会调用该方法获取显示的View
		long time = System.currentTimeMillis();
		View view = inflater.inflate(getLayoutId(),container,false);
		ARouter.getInstance().inject(this);
		unbinder = ButterKnife.bind(this,view);
		initView();
		LogUtils.e(this.toString()+"页面加载使用："+(System.currentTimeMillis()-time));
		return view;
	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
		unbinder.unbind();
	}


	/**
	 * 设置页面名字 用于友盟统计
	 */
	public abstract String getPageName();
	/**
	 * 设置layoutId
	 * @return
	 */
	protected abstract int getLayoutId();

	/**
	 * 初始化视图
	 */
	protected abstract void initView();

	/**
	 * @param mActivity 传送Activity的
	 */
	public void intent(Class mActivity) {
		Intent intent = new Intent(getContext(), mActivity);
		startActivity(intent);
	}

	/**
	 * @param path 传送Activity的
	 */
	public void intentByRouter(String path) {
		ARouter.getInstance().build(path)
				.navigation(getContext());
	}

	/**
	 * @param mActivity 传送Activity的
	 * @param bundle
	 */
	public void intent(Class mActivity, Bundle bundle) {
		Intent intent = new Intent(getContext(), mActivity);
		intent.putExtras(bundle);
		startActivity(intent);
	}

	/**
	 * @param path 传送Activity的
	 * @param bundle
	 */
	public void intentByRouter(String path, Bundle bundle) {
		ARouter.getInstance().build(path)
				.with(bundle)
				.navigation(getContext());
	}

}

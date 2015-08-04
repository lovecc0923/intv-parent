package com.uvwxyz.intv.vo;

import java.util.List;

import org.mongodb.morphia.annotations.Id;

/**
 * 用户简历
 * 
 * @author Administrator
 *
 */
public class KResume {

	/**
	 * 简历统计信息
	 * 
	 * @author Administrator
	 *
	 */
	public static class Count {
		private int total;// 总计数
		private int browse;// 简历浏览数
		private int fans;// 简历感兴趣数、关注数
		private int share;// 简历分享数

		public int getTotal() {
			return total;
		}

		public void setTotal(int total) {
			this.total = total;
		}

		public int getBrowse() {
			return browse;
		}

		public void setBrowse(int browse) {
			this.browse = browse;
		}

		public int getFans() {
			return fans;
		}

		public void setFans(int fans) {
			this.fans = fans;
		}

		public int getShare() {
			return share;
		}

		public void setShare(int share) {
			this.share = share;
		}

	}

	/**
	 * 简历活跃度
	 * 
	 * @author Administrator
	 *
	 */
	public static class Activity {
		private int total;// 总活跃度
		private int signIn;// 签到次数
		private int viewJob;// 查看新职位次数
		private int share;// 分享App次数
		private int talk;// 与新职位的Boss沟通次数

		public int getTotal() {
			return total;
		}

		public void setTotal(int total) {
			this.total = total;
		}

		public int getSignIn() {
			return signIn;
		}

		public void setSignIn(int signIn) {
			this.signIn = signIn;
		}

		public int getViewJob() {
			return viewJob;
		}

		public void setViewJob(int viewJob) {
			this.viewJob = viewJob;
		}

		public int getShare() {
			return share;
		}

		public void setShare(int share) {
			this.share = share;
		}

		public int getTalk() {
			return talk;
		}

		public void setTalk(int talk) {
			this.talk = talk;
		}

	}

	@Id
	private int userId;// 用户Id
	private int jobId;// 期望职位
	private int salary;// 薪水要求：0=面议；1=按区间
	private int min;// 最低薪资
	private int max;// 最高薪资
	private int jobStatus;// 求职状态
	private List<String> tags;// 我的亮点
	private String forte;// 我的优势
	private List<KResumeWork> workList;// 工作经历
	private List<KResumeEdu> eduList;// 教育经历

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getJobId() {
		return jobId;
	}

	public void setJobId(int jobId) {
		this.jobId = jobId;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public int getMin() {
		return min;
	}

	public void setMin(int min) {
		this.min = min;
	}

	public int getMax() {
		return max;
	}

	public void setMax(int max) {
		this.max = max;
	}

	public int getJobStatus() {
		return jobStatus;
	}

	public void setJobStatus(int jobStatus) {
		this.jobStatus = jobStatus;
	}

	public List<String> getTags() {
		return tags;
	}

	public void setTags(List<String> tags) {
		this.tags = tags;
	}

	public String getForte() {
		return forte;
	}

	public void setForte(String forte) {
		this.forte = forte;
	}

	public List<KResumeWork> getWorkList() {
		return workList;
	}

	public void setWorkList(List<KResumeWork> workList) {
		this.workList = workList;
	}

	public List<KResumeEdu> getEduList() {
		return eduList;
	}

	public void setEduList(List<KResumeEdu> eduList) {
		this.eduList = eduList;
	}

}

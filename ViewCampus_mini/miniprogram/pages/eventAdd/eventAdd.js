// pages/event/eventAdd.js
var QQMapWX = require('../../lib/qqmap-wx-jssdk.js');
var qqmapsdk;
Page({

  /**
   * 页面的初始数据
   */
  data: {
    event_name: '',
    site:{
      latitude: 34.824872,
      longitude: 114.318726,
    },
    coord: '',
    nowDate: '',
    acceptSDate: '',
    acceptEDate: '',
    startDate: '',
    startTime: '00:00',
    endDate: '',
    endTime: '23:59',
    eventContent: ''
  },

  /**
   * 地图点击事件
   */
  GetClick(e){
    var _this = this;
    wx.chooseLocation({
      success: function(res){
        _this.setData({
          site:{
            latitude: res.latitude,
            longitude: res.longitude,
          },
          coord: res.latitude+','+ res.longitude
        })
      },
      fail: function(error){
        //console.error(error)
      },
      complete: function(res){
        //console.log(res)
      }
    });
  },

  //活动名实时处理
  inputSy(e){
    this.setData({
      event_name: e.detail.value
    })
  },

  /**日期预期
   * lim不得超过28天
   */
  GetPreDay(Y,M,D,lim){
    let y = 0;
    let m = 0;
    let d = 0;
    if([1,3,5,7,8,10,12].indexOf(M)>=0){
      d = D+lim<=31? D+lim:D+lim-31
      if(M==12){
        m = D+lim<=31? M:1
        y = D+lim<=31? Y:Y+1
      }else{
        m = D+lim<=31? M:M+1
        y = Y
      }
    };
    if([4,6,9,11].indexOf(M)>=0){
      d = D+lim<=30? D+lim:D+lim-30
      m = D+lim<=30? M:M+1
      y = Y
    };
    if(M == 2){
      y = Y
      if(Y%4==0){
        d = D+lim<=29? D+lim:D+lim-29
        m = D+lim<=29? M:M+1
      }else{
        d = D+lim<=28? D+lim:D+lim-28
        m = D+lim<=28? M:M+1
      }
    };
    return {y,m,d}
  },

  //获取今日时间
  GetDataNow(){
    let mydata = new Date();
    let year = mydata.getFullYear();
    let month = mydata.getMonth()+1;
    let monthS = month<10? "0"+month:""+month;
    let date = mydata.getDate();
    //获得预期日期
    let Day7 = this.GetPreDay(year,month,date,7);
    let monthA7S = Day7.m<10?"0"+Day7.m:Day7.m;
    let Day21 = this.GetPreDay(year,month,date,21);
    let monthA21S = Day21.m<10?"0"+Day21.m:Day21.m;
    this.setData({
      nowDate: year+"-"+monthS+"-"+date,
      acceptSDate: Day7.y+"-"+monthA7S+"-"+Day7.d,
      acceptEDate: Day21.y+"-"+monthA21S+"-"+Day21.d,
      startDate: year+"-"+monthS+"-"+date,
      endDate: Day7.y+"-"+monthA7S+"-"+Day7.d
    })
  },

  //开始日期选择
  SDateChange(e){
    this.setData({
      startDate: e.detail.value
    })
  },
  STimeChange(e){
    this.setData({
      startTime: e.detail.value
    })
  },

  //结束日期选择
  EDateChange(e){
    this.setData({
      endDate: e.detail.value
    })
  },
  ETimeChange(e){
    this.setData({
      endTime: e.detail.value
    })
  },

  //同步并检测输入site
  SiteInput(e){
    let si = e.detail.value;
    try{
      let sla = si.split(',')[0];
      let slo = si.split(',')[1];
      console.log(sla>23)
      if(sla>34.812348 && sla<34.822694 && slo>114.303724 && slo<114.314848){
        this.setData({
          coord: si
        })
      }else{
        wx.showToast({
          title: '出校园啦',
          icon:'error'
        })
      }
    }catch(err){
      wx.showToast({
        title: '请查看输入',
        icon:'error'
      })
    }
  },

  //同步简介
  ContentUnput(e){
    this.setData({
      eventContent: e.detail.value
    })
  },

  //申请活动启动前检测
  ButtonAction(){
    this.ApplyAction()
  },

  //申请活动
  ApplyAction(){
    let _this = this;
    wx.request({
      url: 'https://localhost/events/save',
      method: 'POST',
      data:{
        eventName: _this.data.event_name,
        eventSponsor: wx.getStorageSync('user').nickName,
        eventSite: _this.data.coord,
        eventContent: _this.data.eventContent,
        eventStart: _this.data.startDate+' '+_this.data.startTime+':00',
        eventEnd: _this.data.endDate+' '+_this.data.endTime+':00',
        userId: wx.getStorageSync('proof').openid
      },
      success:(res)=>{
        wx.showToast({
          title: '申请已发送',
          icon: 'success'
        })
      }
    })
  },

  /**
   * 开始时运行
   * 获取当前地址
   */
  getLocation(){
    var _this = this;
    wx.getLocation({
      success: function(res){
        _this.setData({
          site:{
            latitude: res.latitude,
            longitude: res.longitude
          }
        });
      },
      fail: function(error) {
        //console.error(error)
      },
      complete: function(res) {
        //console.log(res);
      }
    })
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad(options) {
    //实例化API核心类
    qqmapsdk = new QQMapWX({
      key:"2X3BZ-PXBCI-C7GGI-UZ5XQ-MZZ4Q-WGBFQ"
    });
    //获取当前坐标
    this.getLocation();
    //获取当前日期
    this.GetDataNow();
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady() {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow() {

  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide() {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload() {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh() {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom() {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage() {

  }
})
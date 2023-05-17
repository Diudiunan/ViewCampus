// pages/events/events.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    IsOn: true,
    ISLOGIN:false,
    eventslistOn:[],
    eventslistOff:[]
  },

  //用户是否登录
  UserIsLogIn(){
    let proof = wx.getStorageSync('proof');
    if(proof==null){
      this.setData({
        ISLOGIN: false,
        eventslistOn:[],
        eventslistOff:[]
      })
    }else{
      this.setData({
        ISLOGIN: true
      })
    }
  },

  //请求用户的活动
  GetEventsOfUser(){
    let _this = this;
    let proof = wx.getStorageSync('proof');
    if(this.data.ISLOGIN==false){
      wx.showToast({
        title: '请登录',
        icon: 'error',
        duration: 1500
      })
    }else{
      //获得激活态活动
      wx.request({
        url: "https://localhost/user/events/"+proof.openid+"/"+true,
        method:'GET',
        success:(res)=>{
          _this.setData({
            eventslistOn: res.data.data
          })
        }
      }),
      //获得失活态活动
      wx.request({
        url: "https://localhost/user/events/"+proof.openid+"/"+false,
        method:"GET",
        success:(res)=>{
          _this.setData({
            eventslistOff: res.data.data
          })
        }
      })
    }
  },

  //切换活动展示
  ChangeIsOn(){
    this.setData({
      IsOn: true
    })
  },

  //切换活动展示
  ChangeIsOff(){
    this.setData({
      IsOn: false
    })
  },

  //导航到查看页面
  LookThisEvent(e){
    let index = e.currentTarget.dataset.index;
    if(this.data.IsOn==true){
      var eList = this.data.eventslistOn[index];
    }else{
      var eList = this.data.eventslistOff[index];
    }
    wx.navigateTo({
      url: "/pages/eventCheck/eventCheck?event="+JSON.stringify(eList),
    })
    console.log(e)
  },

  //导航跳转到eventAdd页面
  goToEventAdd(){
    wx.navigateTo({
      url: '/pages/eventAdd/eventAdd',
    })
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad(options) {
    //用户是否登录
    this.UserIsLogIn();
    //初始时请求用户的活动
    this.GetEventsOfUser();
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
    //用户是否登录
    this.UserIsLogIn();
    //初始时请求用户的活动
    this.GetEventsOfUser();
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
    this.GetEventsOfUser();
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
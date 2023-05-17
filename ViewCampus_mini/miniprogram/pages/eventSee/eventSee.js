// pages/eventSee/eventSee.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    eventList:[],
    latitude:"",
    longitude:"",
    marker: []
  },

  //地图加载
  LoadMap(options){
    //获得此活动id
    let elist = JSON.parse(options.elist);
    let LAS = elist[1].eventSite.split(",")[0];
    let LOS = elist[1].eventSite.split(",")[1];
    let Isrc = (elist[0].eventSponsor=="developer")? "marker_1.png":"marker_2.png";
    let temp = {
      iconPath:"/images/tabs/"+Isrc,
      id: parseInt(elist[0].eventId),
      latitude: parseFloat(LAS),
      longitude: parseFloat(LOS),
      joinCluster: false,
      width: 20,
      height: 20,
      title: elist[0].eventName
    };
    this.setData({
      latitude: LAS,
      longitude: LOS,
      marker: [temp],
      eventList: elist
    })
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad(options) {
    //加载数据
    this.LoadMap(options);
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
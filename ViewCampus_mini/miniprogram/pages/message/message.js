// pages/message/message.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    messageList: []
  },

  //发送请求
  SetRequest(proof){
    let _this = this;
    wx.request({
      url: 'https://localhost/message/user/' + proof.openid,
      method:"GET",
      success:(res)=>{
        wx.setStorageSync('messageList', res.data.data);
        _this.setData({
          messageList: res.data.data
        })
      }
    })
  },

  //获得用户的message消息
  GetUserMessage(){
    let _this = this;
    let proof = wx.getStorageSync('proof');
    if(proof==null){
      wx.showToast({
        title: '请登录',
        icon: "error",
        duration: 1500
      })
    }else{
      let mList = wx.getStorageSync('messageList');
      if(!mList || mList.length==0){
        _this.SetRequest(proof)
      }else{
        _this.setData({
          messageList: mList
        })
      }
    }
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad(options) {
    this.GetUserMessage();
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
    this.GetUserMessage();
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
    let pr = wx.getStorageSync('proof');
    if(pr==null){
      wx.showToast({
        title: '请登录',
        icon: "error",
        duration: 1500
      })
    }else{
      this.SetRequest(pr);
    }
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
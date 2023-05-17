// pages/user/user.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    UserIsLogin: false,
    UserImageSrc: '/images/robot.png',
    UserName: '用户',
    onlyShowOfficial: false,
    onlyShowIndividual: false,
    nearRecommend: false
  },

  //登录失败
  LoginFailure(){
    wx.showModal({
      title: '失败',
      content: '请联系客服',
      complete: (res) => {
        if (res.cancel) {
          
        }
    
        if (res.confirm) {
          
        }
      }
    })
  },

  //用户登录
  LogIn(){
    let _this = this;
    //获取用户头像和名字
    wx.getUserProfile({
      desc: '获取用户信息',
      success: (res) => {
        let user = res.userInfo;
        //存入缓存
        wx.setStorageSync('user', user);
        _this.setData({
          UserName: user.nickName,
          UserImageSrc: user.avatarUrl
        });
        //获取登录凭证
        wx.login({
          success: (res) => {
            let code = res.code;
            wx.request({
              url: 'https://api.weixin.qq.com/sns/jscode2session?appid=wx9658beafa11b2ecc&secret=7fb897cbe1c3f3284b92f33be8f9db37&js_code='+code+'&grant_type=authorization_code',
              success:(res)=>{
                let proof = res.data;
                wx.request({
                  url: 'https://localhost/user/exist/'+proof.openid,
                  method: "GET",
                  success:(res)=>{
                    //是否已注册
                    if(!res.data.res){
                      //未注册
                      wx.request({
                        url: 'https://localhost/user/save',
                        method: "POST",
                        data:{
                          openid:proof.openid,
                          name:user.nickName,
                          session_key: proof.session_key
                        },
                        success:(res)=>{
                          wx.setStorageSync('proof', proof);
                          if(res.data.res){
                            _this.setData({
                              UserIsLogin: true,
                              UserOpenId: proof.openid
                            })
                          }else{
                            _this.LoginFailure
                          }
                        }
                      })
                    }else{
                      //已注册
                      wx.setStorageSync('proof', proof);
                      _this.setData({
                        UserIsLogin: true,
                        UserOpenId: proof.openid
                      }),
                      wx.showToast({
                        title: '登录成功',
                        icon: "success",
                        duration: 1000
                      })
                    }
                  }
                })
              }
            })
          },
        })
      },
    })
  },

  //用户退出
  LogOut(){
    this.setData({
      UserIsLogin: false
    }),
    wx.setStorageSync('user', null),
    wx.setStorageSync('proof', null)
  },

  //切换活动过滤
  ShowOfficial(e){
    wx.setStorageSync('onlyShowOfficial', e.detail.value),
    this.setData({
      onlyShowOfficial: e.detail.value
    })
  },

  //切换活动过滤
  ShowIndividual(e){
    wx.setStorageSync('onlyShowIndividual', e.detail.value),
    this.setData({
      onlyShowIndividual: e.detail.value
    })
  },

  //切换活动最近推荐模式
  NearRecommend(e){
    wx.setStorageSync('nearRecommend', e.detail.value),
    this.setData({
      nearRecommend: e.detail.value
    })
  },

  //初始化
  UserSart(){
    //获得用户缓存内设置
    let O = wx.getStorageSync('onlyShowOfficial');
    let S = wx.getStorageSync('onlyShowIndividual');
    let R = wx.getStorageSync('nearRecommend');
    this.setData({
      onlyShowOfficial:O==null?false:O,
      onlyShowIndividual:S==null?false:S,
      nearRecommend:R==null?false:R
    })
    //获取用户缓存
    let user = wx.getStorageSync('user');
    let proof = wx.getStorageSync('proof');
    if(!(user==null)&!(proof==null)){
      this.setData({
        UserIsLogin: true,
        UserName: user.nickName,
        UserImageSrc: user.avatarUrl,
        UserOpenId: proof.openid
      })
    }
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad(options) {
    //初始化
    this.UserSart();
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
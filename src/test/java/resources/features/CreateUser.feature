# language: vi

@createuser
Tính năng: Đăng ký tài khoản

  # neu muon chay truc tiep file feature ma co report cucumber
  # thi chon modify run configtion, tai phan argument  them --plugin json:target/site/cucumber.json
  @kichban1
  Kịch bản: tao user
  Cho truy cập vào trang "https://demo.guru99.com/v4/"
  Và ấn vào trang tạo tài khoản
  Khi điền email vào ô textbox
  Và ấn submit
  Khi lấy tài khoản và mật khẩu
  Và chở về trang login
  Và đăng nhập với tài khoản và mật khẩu vừa tạo
  Thì truy cập được vào trang homepage


  Kịch bản: tao user 2
    Cho truy cập vào trang "https://demo.guru99.com/v4/"
    Và ấn vào trang tạo tài khoản
    Khi điền email vào ô textbox
    Và ấn submit
    Khi lấy tài khoản và mật khẩu
    Và chở về trang login
    Và đăng nhập với tài khoản và mật khẩu vừa tạo
    Thì truy cập được vào trang homepage



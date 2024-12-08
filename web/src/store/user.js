import $ from 'jquery'

export default {
    state: {
        id: "",
        username: "",
        password: "",
        photo: "",
        token: "",
        email: "",
        phone: "",
        real_name: "",
        gender: "",
        is_login: false,
        pulling_info: true, //是否正在拉取信息
    },
    getters: {
    },
    mutations: {
        updateUser(state, user) {
            state.id = user.id;
            state.username = user.username;
            state.photo = user.photo;
            state.is_login = user.is_login;
            state.email = user.email;
            state.gender = user.gender;
            state.real_name = user.real_name;
            state.phone = user.phone
        },
        updateToken(state, token) {
            state.token = token;
        },
        logout(state) {
            state.id = "";
            state.username = "";
            state.photo = "";
            state.token = "";
            state.is_login = false;
        },
        updatePullingInfo(state, pulling_info) {
            state.pulling_info = pulling_info;
        },
    },
    actions: {
        login(context, data) {
            $.ajax({
                url: "http://127.0.0.1:3000/user/account/token/",
                type: "post",
                data: {
                  username: data.username,
                  password: data.password,
                },
                success(resp) {
                    if (resp.error_message === "success") {
                        localStorage.setItem("jwt_token", resp.token);
                        context.commit("updateToken", resp.token);
                        data.success(resp);
                    } else {
                        data.error(resp);
                    }
                },
                error(resp) {
                  data.error(resp); 
                }
              });
        },
        getinfo(context, data) {
            $.ajax({
                url: "http://127.0.0.1:3000/user/account/info/",
                type: "get",
                headers: {
                  Authorization: "Bearer " + context.state.token,
                },
                //成功就更新用户。
                success(resp) {
                    if (resp.error_message === "success") {
                        context.commit("updateUser", {
                            ...resp,
                            is_login: true,
                        });
                        data.success(resp);
                    } else {
                        console.error("Error message from server:", resp.error_message);
                        data.error(resp);
                    }
                },
                error(resp) {
                    data.error(resp);
                }
              });
        },
        logout(context) {
            localStorage.removeItem("jwt_token");
            context.commit("logout");
        },
        // 更新用户信息
    updateUserInfo(context, data) {
        
        $.ajax({
          url: "http://127.0.0.1:3000/user/account/update/",
          type: "post",
          headers: {
            Authorization: "Bearer " + context.state.token,
            "Content-Type": "application/json",
          },
          
          data: JSON.stringify({ // 使用 JSON.stringify 转换数据为 JSON 格式
            username: data.username, 
            real_name: data.real_name,
            email: data.email,
            phone: data.phone,
            gender: data.gender
          }),
          
          success(resp) {
            if (resp.error_message === "success") {
                
                const updatedUser = {
                    ...context.state,      // 保留现有用户数据
                    ...data,               // 前端传入的更新数据
                    id: resp.id,           // 后端返回的 id
                };
              context.commit("updateUser", updatedUser); // 更新状态
              data.success(resp);
            } else {
              data.error(resp);
            }
          },
          error(resp) {
            data.error(resp);
          },
        });
      },
    },       
    modules: {
    }
}

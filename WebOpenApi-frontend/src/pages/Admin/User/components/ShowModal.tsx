import {
    DrawerForm,
    ProFormInstance,
    ProFormText,
} from '@ant-design/pro-components';
import '@umijs/max';
import {Avatar, Form, Input} from 'antd';
import React, { useEffect, useRef } from 'react';

export type Props = {
    values: API.UserVo;
    setVisible: (visible: boolean) => void;
    visible: boolean;
};

const ShowModal: React.FC<Props> = (props) => {
    const { values, setVisible, visible } = props;
    const formRef = useRef<ProFormInstance>();

    useEffect(() => {
        formRef.current?.setFieldsValue(values);
    }, [values]);

    return (
        <DrawerForm<API.InterfaceInfoVo>
            formRef={formRef}
            formKey="update-modal-form"
            autoFocusFirstInput
            onOpenChange={setVisible}
            title="查看用户"
            open={visible}
        >
          <ProFormText name="userAccount" label="账户名" initialValue={values.userAccount} disabled />
          <ProFormText name="userName" label="昵称" initialValue={values.userName} disabled />
          <Form.Item name="userAvatar" label="头像">
            <Avatar shape="square" size={64} src={<img src={values.userAvatar} alt="avatar" />} />
          </Form.Item>
          <Form.Item name="userProfile" label="简介">
            <Input.TextArea defaultValue={values.userProfile} disabled />
          </Form.Item>
          <ProFormText name="email" label="邮箱" initialValue={values.email} disabled />
          <ProFormText name="qq" label="QQ" initialValue={values.qq} disabled />
          <ProFormText name="userRole" label="角色" initialValue={values.userRole} disabled />
          <ProFormText name="accessKey" label="accessKey" initialValue={values.accessKey} disabled />
          <ProFormText name="secretKey" label="secretKey" initialValue={values.secretKey} disabled />
        </DrawerForm>
    );
};
export default ShowModal;

import { PageContainer } from '@ant-design/pro-components';
import React, {useEffect, useState} from 'react';
import {
  getInterfaceInfoUsingGET, invokeInterfaceInfoUsingPOST,
} from "@/services/WebOpenApi-backend/interfaceInfoController";
import {useParams} from "react-router";
import {Button, Card, Descriptions, Form, Input, message} from "antd";
import moment from "moment";

const Index: React.FC = () => {
  const [loading, setLoading] = useState(false);
  const [invokeLoading, setInvokeLoading] = useState(false);
  const [data, setData] = useState<API.InterfaceInfoVo>();
  const [invokeRes, setInvokeRes] = useState<any>();
  const params = useParams()

  const loadData = async () => {
    if (!params.id) {
      message.error("接口不存在");
      return;
    }
    setLoading(true);
    try {
      const res = await getInterfaceInfoUsingGET({
        id: Number(params.id)
      })
      setData(res.data);
    }catch (error: any) {
      return [];
    }
    setLoading(false);
  }

  useEffect(() => {
    loadData();
  },[])

  //接口调用提交方法
  const onFinish = async (values: any) => {
    if (!params.id) {
      message.error("接口不存在");
      return;
    }
    setInvokeLoading(true);
    try {
      const res = await invokeInterfaceInfoUsingPOST({
        id: params.id,
        ...values
      })
      setInvokeRes(res.data);
      message.success("调用成功");
    }catch (error: any) {
      return {};
    }
    setInvokeLoading(false);

  }

  return (
    <PageContainer title={"接口文档查看"}>
      <Card loading={loading}>
        {data ? (
          <Descriptions title={data.name} column={1}>
            <Descriptions.Item label="描述">{data.description}</Descriptions.Item>
            <Descriptions.Item label="接口状态">{data.status ? '正常' : '关闭'}</Descriptions.Item>
            <Descriptions.Item label="请求类型">{data.method}</Descriptions.Item>
            <Descriptions.Item label="接口地址">{data.uri}</Descriptions.Item>
            <Descriptions.Item label="请求头">{data.requestHeader}</Descriptions.Item>
            <Descriptions.Item label="响应头">{data.responseHeader}</Descriptions.Item>
            <Descriptions.Item label="发布时间">
              {moment(data.createTime).format('YYYY-MM-DD HH:mm:ss')}
            </Descriptions.Item>
            <Descriptions.Item label="更新时间">
              {data.updateTime ?
                moment(data.updateTime).format('YYYY-MM-DD HH:mm:ss') : ''}
            </Descriptions.Item>
          </Descriptions>
        ) : (
          <>接口不存在</>
        )}
      </Card>
      <Card title={"接口测试"}>
        <Form
          name={"invoke"}
          layout={"vertical"}
          onFinish={onFinish}
        >
          <Form.Item
            label={"请求参数"}
            name={"userRequestParams"}
          >
            <Input.TextArea value={data?.requestParams}/>
          </Form.Item>
          <Form.Item wrapperCol={{span: 16}}>
            <Button type={"primary"} htmlType={"submit"}>
              调用
            </Button>
          </Form.Item>
        </Form>
      </Card>
      <Card title={"调用结果"} loading={invokeLoading}>
        {invokeRes}
      </Card>
    </PageContainer>
  );
};

export default Index;

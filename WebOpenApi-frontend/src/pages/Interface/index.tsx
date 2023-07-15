import { PageContainer } from '@ant-design/pro-components';
import React, {useEffect, useState} from 'react';
import {useParams} from "react-router";
import {Badge, Button, Card, Descriptions, Divider, Form, Input, message, Tag} from "antd";
import moment from "moment";
import {
  getDetailInterfaceInfoUsingGET,
  invokeInterfaceUsingPOST
} from "@/services/WebOpenApi-backend/interfaceController";

const Index: React.FC = () => {
  const [loading, setLoading] = useState(false);
  const [invokeLoading, setInvokeLoading] = useState(false);
  const [data, setData] = useState<API.InterfaceDetailVo>();
  const [invokeRes, setInvokeRes] = useState<any>();
  const params = useParams()

  const loadData = async () => {
    if (!params.id) {
      message.error("接口不存在或已下线");
      return;
    }
    setLoading(true);
    try {
      const res = await getDetailInterfaceInfoUsingGET({
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
      const res = await invokeInterfaceUsingPOST({
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
    <PageContainer title={"接口在线调试"}>
      <Card loading={loading}>
        {data ? (
          <Descriptions title={data.name} column={1}>
            <Descriptions.Item label="描述">{data.description}</Descriptions.Item>
            <Descriptions.Item label="接口状态">{data.status ? (
              <Badge status="success" text={'在线'} />
            ) : (
              <Badge status="default" text={'已下线'} />
            )}</Descriptions.Item>
            <Descriptions.Item label="请求类型"><Tag color={data.method === 'GET' ? 'success' :
              data.method === 'POST' ? 'processing' : data.method === 'PUT' ? 'warning' : 'error'} key={data.method}>
              {data.method}
            </Tag></Descriptions.Item>
            <Descriptions.Item label="接口地址">{data.uri}</Descriptions.Item>
            <Descriptions.Item label="发布时间">
              {moment(data.createTime).format('YYYY-MM-DD HH:mm:ss')}
            </Descriptions.Item>
          </Descriptions>
        ) : (
          <>接口不存在或已下线</>
        )}
      </Card>
      <Divider />
      <Card title={"在线调试"}>
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
      <Divider />
      <Card title={"调试结果"} loading={invokeLoading}>
        {invokeRes}
      </Card>
    </PageContainer>
  );
};

export default Index;

import { GithubOutlined } from '@ant-design/icons';
import { DefaultFooter } from '@ant-design/pro-components';
import '@umijs/max';
import React from 'react';
const Footer: React.FC = () => {
  const defaultMessage = '2023';
  const currentYear = new Date().getFullYear();
  const beian = '鄂ICP备2021019038号-2';
  const beianUrl = 'https://beian.miit.gov.cn/#/Integrated/index';
  return (
    <DefaultFooter
      style={{
        background: 'none',
      }}
      // @ts-ignore
      copyright={
        <>
          {defaultMessage}-{currentYear} | {' '}
          <a href={beianUrl} target="_blank" rel="noreferrer">
            {beian}
          </a>
        </>
      }
      links={[
        {
          key: 'WebOpenApi',
          title: 'WebOpenApi',
          href: 'https://openapi.zhnblog.icu',
          blankTarget: true,
        },
        {
          key: 'github',
          title: <GithubOutlined />,
          href: 'https://github.com/newzhn/WebOpenApi',
          blankTarget: true,
        },
        {
          key: 'zhnblog',
          title: 'zhnblog',
          href: 'https://zhnblog.icu',
          blankTarget: true,
        },
      ]}
    />
  );
};
export default Footer;

## 如何给开源(本)项目贡献代码

> 此方法也适用于你在其它仓库贡献代码！！！

一般我们会接触到如下两种：

- 代码仓库管理者给你添加该仓库的写入权限，这样的话可以直接`push`。
- 对于不能直接`push`的仓库，采用经典的`fork & new pr`来提交代码。

### fork & pull request

本仓库采用了最常见的开发模型，稳定的代码会提交到`main`分支，其它特性全部在`dev`分支进行开发，功能完善/稳定后，会合并到`main`分支。

> 注意，并不是所有仓库的都叫`dev`，你可以尝试追踪 git 树以了解此库具有相同特性的分支。

假如现在需要在其分支`dev`上提交代码（修复 bug、优化性能、新增功能），那么我建议你这样做：

* 克隆代码到本地

```
git clone https://github.com/besscroft/pisces-cloud.git
```

* 切换到远程分支`dev`（远程库默认名字为`origin`）

```
git checkout origin/dev
```

* 基于远程分支`dev`新建本地分支`dev`（注意远程分支和本地分支的区别，名字一样，但是一个是远程，一个是本地）

```bash
git checkout -b dev

# 或者
git branch dev
git checkout dev
```

* 在该分支提交你的更改，然后提交

```bash
git commit -m ":bug:fix bug"
```

* 由于你此时没有直接 push 到 origin 的权限，所有需要先对该仓库进行`fork`，然后在本地添加一个新的推送地址

```bash
git remote add upstream git@github.com:<your-name>/pisces-cloud.git
```

* 推送本地分支到自己的`pisces-cloud fork`库（需要先做合并，因为此时远程分支管`dev`可能合并了其他代码）

```bash
git fetch origin
git merge origin/dev

git push upstream dev
```

* 这样你的`pisces-cloud fork`库的`dev`分支包含了你的最新更改，点击上面的`new pull request`就可以推送请求了。注意推送的来源和目的地，如果不对需要点击`Edit`进行修改，另外可以点击下面的标签`file changed`查看具体的变动，确认无误后填写 pull request 的标题和具体内容，点击`create pull request`绿色按钮推送就可以了

如果评审人员给出了反馈需要继续修正代码，可以从第六步重新开始，这样所有的提交都会显示到同一个`pull request`中，如果想发起一个全新的`pull request`，可以拉出一个新的分支，然后重复第六步开始的工作。

> 注：提交到本项目的代码，必须有如下要求：
>
> This commit was created on GitHub.com and signed with GitHub’s **verified signature**.